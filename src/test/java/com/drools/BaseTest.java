package com.drools;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class BaseTest {

    public KieSession getKieSession(String fileName) {
        //从classPath加载


        Resource resource = ResourceFactory.newClassPathResource("rule/" + fileName + ".drl", this.getClass());
        //从文件加载
        // Resource resource = ResourceFactory.newFileResource()
        //从字符串加载
        // ResourceFactory.newReaderResource
        //加载远程rule
        //ResourceFactory.newUrlResource("http://localhost:8080/test/Sample.drl")
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //  knowledgeBuilder.add(ResourceFactory.newUrlResource("http://localhost:8080/test/Sample.drl"), ResourceType.DRL);

        knowledgeBuilder.add(resource, ResourceType.DRL);

        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }
        InternalKnowledgeBase kbbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbbase.addPackages(knowledgeBuilder.getKnowledgePackages());

        return kbbase.newKieSession();
    }

    public static <T> void execute(KieSession kieSession, T... pojos) {
        for (T pojo : pojos) {
            kieSession.insert(pojo);
        }
        int count = kieSession.fireAllRules();

        System.out.println("总执行了" + count + "条规则------------------------------");
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
