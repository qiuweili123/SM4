package com.drools;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class DecisionMapTest {
/*    public static KieSession getKieSessionByXls(String xlsPath) {

        DecisionTableConfiguration dtc = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtc.setInputType(DecisionTableInputType.XLS);
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(xlsPath, DecisionMapTest.class), ResourceType.DTABLE, dtc);

        KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase.newStatefulKnowledgeSession();
    }*/


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

        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }
        InternalKnowledgeBase kbbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbbase.addPackages(knowledgeBuilder.getKnowledgePackages());

        return kbbase.newKieSession();
       /* knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase.newStatefulKnowledgeSession();*/
    }

    /**
     * 将XLS文件编译为DRL字符串
     *
     * @param fileName 决策表XLS文件路径
     * @return DRL字符串
     * @throws Exception
     */
    public static String compile2DRL(String fileName) throws Exception {

        InputStream fis = ResourceFactory.newClassPathResource(fileName).getInputStream();
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        return converter.compile(fis, InputType.XLS);
    }

    public static String getDrl() {

        return "package com.secbro.drools.cdss.common;\n" +
                "//generated from Decision Table\n" +
                "import java.lang.Integer;\n" +
                "import java.lang.String;\n" +
                "import java.util.HashMap;\n" +
                " function void printName(String name,String desc) {\n" +
                "            System.out.println(\"name:\"+name+\" desc:\"+ desc);\n" +
                "        }\n" +
                "// rule values at B11, header at B6\n" +
                "rule \"prerumeRule_11\"\n" +
                "\tsalience 65535\n" +
                "\twhen\n" +
                "\t\t$map:HashMap($map.get(\"param1\") == \"Orthopedics\", $map.get(\"param2\")>10,   $map.get(\"param2\")<20, $map.get(\"payFee\")>=100.22)\n" +
                "\tthen\n" +
                "\t\tSystem.out.println(\"!!!!!!!!!!!!!!!!!!!!!\");\n" +
                "$map.put(\"ret1\",100);\n" +
                "end\n" +
                "\n" +
                "// rule values at B12, header at B6\n" +
                "rule \"prerumeRule_12\"\n" +
                "\tsalience 65534\n" +
                "\twhen\n" +
                "\t\t$map:HashMap($map.get(\"param1\") == \"Orthopedics\", $map.get(\"param2\")>30,   $map.get(\"param2\")<60, $map.get(\"payFee\")>=100)\n" +
                "\tthen\n" +
                "\t\tSystem.out.println(\"!!!!!!!!!!!!!!!!!!!!!\");\n" +
                "$map.put(\"ret1\",600);\n" +
                "end\n";
    }

    /**
     * 注入POJO，执行规则
     *
     * @param kieSession
     * @param pojos
     */
    public static void execute(KieSession kieSession, Map... pojos) {
        for (Map pojo : pojos) {
            kieSession.insert(pojo);
        }
        int count = kieSession.fireAllRules();

        System.out.println("总执行了" + count + "条规则------------------------------");
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public static void main(String[] args) {
        KieSession kieSession = null;
        try {

            String xlsPath = "droolsMap.xlsx";

            String drlStr = getDrl();//compile2DRL(xlsPath);


            // KieSession kieSession = DroolsSvs.getKieSessionByXls(xlsPath);
              kieSession = getKieSessionByDrlString(drlStr);
            Map<String, Object> p1 = new HashMap<>();

            p1.put("param1", "Orthopedics");
            p1.put("param2", "32");
            p1.put("param3", "1000");
            p1.put("payFee", "101.01");
            /*   DroolsSpreadsheet p1 = new DroolsSpreadsheet();
            p1.setParam1("Orthopedics");
            p1.setParam2("15");
            p1.setParam3("1000");
            p1.setParam4("B");
         DroolsSpreadsheet p2 = new DroolsSpreadsheet();
            p2.setParam1("Orthopedics");
            p2.setParam2("22");
            p2.setParam3("900");
            p2.setParam4("B");
*/


            execute(kieSession, p1);
            Object param2 = p1.get("param2");

            int p2 = Integer.parseInt(param2.toString());
            System.out.println((p2 > 20) + "after p1::" + p1);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (kieSession != null)
                kieSession.dispose();
        }
    }
}
