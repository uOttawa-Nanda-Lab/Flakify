/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.jboss.as.jacorb;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jboss.as.controller.ModelVersion;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.model.test.FailedOperationTransformationConfig;
import org.jboss.as.model.test.ModelFixer;
import org.jboss.as.model.test.ModelTestControllerVersion;
import org.jboss.as.model.test.ModelTestUtils;
import org.jboss.as.subsystem.test.AbstractSubsystemTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.as.subsystem.test.KernelServicesBuilder;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
public class JacORBTransformersTestCase extends AbstractSubsystemTest {

    

    //eap62=1.3
    //eap63=1.4
    //eap64=1.4

    private void testTransformers(ModelTestControllerVersion controllerVersion, ModelVersion modelVersion) throws Exception {
        KernelServicesBuilder builder = createKernelServicesBuilder(AdditionalInitialization.ADMIN_ONLY_HC)
                .setSubsystemXmlResource("subsystem-transform.xml");

        // Add legacy subsystems
        builder.createLegacyKernelServicesBuilder(AdditionalInitialization.ADMIN_ONLY_HC, controllerVersion, modelVersion)
                .addMavenResourceURL("org.jboss.as:jboss-as-jacorb:" + controllerVersion.getMavenGavVersion())
                .configureReverseControllerCheck(AdditionalInitialization.ADMIN_ONLY_HC, null);

        KernelServices mainServices = builder.build();
        KernelServices legacyServices = mainServices.getLegacyServices(modelVersion);
        Assert.assertTrue(mainServices.isSuccessfulBoot());
        Assert.assertTrue(legacyServices.isSuccessfulBoot());

        checkSubsystemModelTransformation(mainServices, modelVersion, new ModelFixer() {
            @Override
            public ModelNode fixModel(ModelNode modelNode) {
                //modelNode.get(JacORBSubsystemDefinitions.PERSISTENT_SERVER_ID.getName()).set(JacORBSubsystemDefinitions.PERSISTENT_SERVER_ID.getDefaultValue());
                modelNode.get(JacORBSubsystemDefinitions.INTEROP_CHUNK_RMI_VALUETYPES.getName()).set(JacORBSubsystemDefinitions.INTEROP_CHUNK_RMI_VALUETYPES.getDefaultValue());
                return modelNode;
            }
        });
    }

    @Test public void testTransformersSecurityClientEAP640() throws Exception{testTransformersSecurityClient(ModelTestControllerVersion.EAP_6_4_0,ModelVersion.create(1,4,0));}


}