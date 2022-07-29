/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.security;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jboss.as.controller.ModelVersion;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.PathElement;
import org.jboss.as.model.test.FailedOperationTransformationConfig;
import org.jboss.as.model.test.ModelTestControllerVersion;
import org.jboss.as.model.test.ModelTestUtils;
import org.jboss.as.subsystem.test.AbstractSubsystemBaseTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.as.subsystem.test.KernelServicesBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tomaz Cerar (c) 2017 Red Hat Inc.
 */
public class SecurityTransformersTestCase extends AbstractSubsystemBaseTest {

    @Test public void testTransformersEAP70() throws Exception{testTransformers(ModelTestControllerVersion.EAP_7_0_0);}

    private void testTransformers(ModelTestControllerVersion controllerVersion) throws Exception {
        ModelVersion version = ModelVersion.create(1, 3, 0);

        final String mavenGavVersion = controllerVersion.getMavenGavVersion();
        final String artifactId;
        if (controllerVersion.isEap() && mavenGavVersion.equals(controllerVersion.getCoreVersion())) {
               /* EAP 6 */
            artifactId = "jboss-as-security";
        } else {
            artifactId = "wildfly-security";
        }

        String mavenGav = String.format("%s:%s:%s", controllerVersion.getMavenGroupId(), artifactId, controllerVersion.getMavenGavVersion());

        testTransformers(controllerVersion, version, mavenGav);
        testReject(controllerVersion, version, mavenGav);
    }

    private void testTransformers(ModelTestControllerVersion controllerVersion, ModelVersion targetVersion, String mavenGAV) throws Exception {
        //Boot up empty controllers with the resources needed for the ops coming from the xml to work
        KernelServicesBuilder builder = createKernelServicesBuilder(createAdditionalInitialization())
                .setSubsystemXmlResource("security-transformers_2.0.xml");
        builder.createLegacyKernelServicesBuilder(null, controllerVersion, targetVersion)
                .addMavenResourceURL(mavenGAV)
                .configureReverseControllerCheck(createAdditionalInitialization(), null)
                .dontPersistXml();

        KernelServices mainServices = builder.build();
        assertTrue(mainServices.isSuccessfulBoot());
        assertTrue(mainServices.getLegacyServices(targetVersion).isSuccessfulBoot());

        checkSubsystemModelTransformation(mainServices, targetVersion, null);
        mainServices.shutdown();
    }
}
