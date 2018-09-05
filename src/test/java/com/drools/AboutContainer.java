package com.drools;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class AboutContainer {

    public void testCreate() {
        KieServices kieServices = KieServices.Factory.get();

        kieServices.getRepository();

        kieServices.getKieClasspathContainer();
        ReleaseId releaseId = kieServices.newReleaseId("org.acme", "myartifact", "1.0-SNAPSHOT");
        KieContainer kContainer = kieServices.newKieContainer(releaseId);
        KieScanner kScanner = kieServices.newKieScanner(kContainer);
//
// Start the KieScanner polling the Maven repository every 10 seconds
        kScanner.start(10000L);

        KieBase kieBase = kContainer.getKieBase();
        StatelessKieSession statelessKieSession = kieBase.newStatelessKieSession();

    }
}
