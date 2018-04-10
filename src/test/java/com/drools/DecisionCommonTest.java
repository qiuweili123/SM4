package com.drools;


import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

public class DecisionCommonTest {
    public static KieSession getKieSessionByXls(String xlsPath) {
        DecisionTableConfiguration dtc = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtc.setInputType(DecisionTableInputType.XLS);
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(xlsPath, DecisionCommonTest.class), ResourceType.DTABLE, dtc);
        KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase.newStatefulKnowledgeSession();
    }

    /**
     * 加载DRL字符串，获取KieSession
     *
     * @param drlStr DRL字符串
     * @return
     */
    public static KieSession getKieSessionByDrlString(String drlStr) {
        Resource resource = ResourceFactory.newReaderResource((Reader) new StringReader(drlStr));
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(resource, ResourceType.DRL);

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase.newStatefulKnowledgeSession();
    }

    /**
     * 将XLS文件编译为DRL字符串
     *
     * @param xlsPath 决策表XLS文件路径
     * @return DRL字符串
     * @throws Exception
     */
    public static String compile2DRL(String fileName) throws Exception {

        InputStream fis = ResourceFactory.newClassPathResource(fileName).getInputStream();
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        return converter.compile(fis, InputType.XLS);
    }

    /**
     * 注入POJO，执行规则
     *
     * @param kieSession
     * @param pojos
     */
    public static void execute(KieSession kieSession, DroolsSpreadsheet... pojos) {
        for (DroolsSpreadsheet pojo : pojos) {
            kieSession.insert(pojo);
        }
        int count = kieSession.fireAllRules();

        System.out.println("总执行了" + count + "条规则------------------------------");
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public static void main(String[] args) {
        try {
            String xlsPath = "droolsCommon.xlsx";

            String drlStr = compile2DRL(xlsPath);

            System.out.println(drlStr);
            // KieSession kieSession = DroolsSvs.getKieSessionByXls(xlsPath);
            KieSession kieSession = getKieSessionByDrlString(drlStr);

            DroolsSpreadsheet p1 = new DroolsSpreadsheet();
            p1.setParam1("Orthopedics");
            p1.setParam2("15");
            p1.setParam3("1000");
            p1.setParam4("B");
            DroolsSpreadsheet p2 = new DroolsSpreadsheet();
            p2.setParam1("Orthopedics");
            p2.setParam2("22");
            p2.setParam3("900");
            p2.setParam4("B");


            System.out.println("before p1::" + p1);
            execute(kieSession, p1, p2);
            System.out.println("after p1::" + p1);
            System.out.println("after p2::" + p2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
