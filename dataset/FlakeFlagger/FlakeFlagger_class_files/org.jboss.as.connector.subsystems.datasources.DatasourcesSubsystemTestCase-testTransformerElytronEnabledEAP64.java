/*
* JBoss, Home of Professional Open Source.
* Copyright 2011, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.as.connector.subsystems.datasources;

import static org.jboss.as.connector.subsystems.datasources.Constants.AUTHENTICATION_CONTEXT;
import static org.jboss.as.connector.subsystems.datasources.Constants.CREDENTIAL_REFERENCE;
import static org.jboss.as.connector.subsystems.datasources.Constants.ELYTRON_ENABLED;
import static org.jboss.as.connector.subsystems.datasources.Constants.RECOVERY_AUTHENTICATION_CONTEXT;
import static org.jboss.as.connector.subsystems.datasources.Constants.RECOVERY_CREDENTIAL_REFERENCE;
import static org.jboss.as.connector.subsystems.datasources.Constants.RECOVERY_ELYTRON_ENABLED;
import static org.jboss.as.connector.subsystems.datasources.Constants.TRACKING;

import java.io.IOException;
import java.util.List;

import org.jboss.as.connector._private.Capabilities;
import org.jboss.as.connector.logging.ConnectorLogger;
import org.jboss.as.controller.ModelVersion;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.security.CredentialReference;
import org.jboss.as.model.test.FailedOperationTransformationConfig;
import org.jboss.as.model.test.FailedOperationTransformationConfig.AttributesPathAddressConfig;
import org.jboss.as.model.test.ModelTestControllerVersion;
import org.jboss.as.model.test.ModelTestUtils;
import org.jboss.as.model.test.SingleClassFilter;
import org.jboss.as.subsystem.test.AbstractSubsystemBaseTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.as.subsystem.test.KernelServicesBuilder;
import org.jboss.as.subsystem.test.LegacyKernelServicesInitializer;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 * @author <a href="stefano.maestri@redhat.com>Stefano Maestri</a>
 */
public class DatasourcesSubsystemTestCase extends AbstractSubsystemBaseTest {

    protected AdditionalInitialization createAdditionalInitialization() {
        // Create a AdditionalInitialization.MANAGEMENT variant that has all the external
        // capabilities used by the various configs used in this test class
        return AdditionalInitialization.withCapabilities(
                Capabilities.AUTHENTICATION_CONTEXT_CAPABILITY + ".DsAuthCtxt",
                Capabilities.AUTHENTICATION_CONTEXT_CAPABILITY + ".CredentialAuthCtxt",
                CredentialReference.CREDENTIAL_STORE_CAPABILITY + ".test-store"
        );
    }

    @Test public void testTransformerElytronEnabledEAP64() throws Exception{testTransformerElytronEnabled("datasources-elytron-enabled_5_0.xml",ModelTestControllerVersion.EAP_6_4_0,ModelVersion.create(1,3,0));}
    private KernelServices initialKernelServices(KernelServicesBuilder builder, ModelTestControllerVersion controllerVersion, final ModelVersion modelVersion) throws Exception {
        LegacyKernelServicesInitializer initializer = builder.createLegacyKernelServicesBuilder(null, controllerVersion, modelVersion);
        String mavenGroupId = controllerVersion.getMavenGroupId();
        String artifactId = "wildfly-connector";
        if (controllerVersion.isEap() && controllerVersion.getMavenGavVersion().equals(controllerVersion.getCoreVersion())) { // EAP 6
            artifactId = "jboss-as-connector";
        }
        initializer.addMavenResourceURL(mavenGroupId + ":" + artifactId + ":" + controllerVersion.getMavenGavVersion());
        initializer.addMavenResourceURL("org.jboss.ironjacamar:ironjacamar-spec-api:1.0.28.Final")
                .addMavenResourceURL("org.jboss.ironjacamar:ironjacamar-common-api:1.0.28.Final")
                .setExtensionClassName("org.jboss.as.connector.subsystems.datasources.DataSourcesExtension")
                .excludeFromParent(SingleClassFilter.createFilter(ConnectorLogger.class));

        KernelServices mainServices = builder.build();
        Assert.assertTrue(mainServices.isSuccessfulBoot());
        KernelServices legacyServices = mainServices.getLegacyServices(modelVersion);
        Assert.assertTrue(legacyServices.isSuccessfulBoot());
        Assert.assertNotNull(legacyServices);
        return mainServices;
    }

    private static class RejectUndefinedAttribute extends AttributesPathAddressConfig<RejectUndefinedAttribute> {

        private RejectUndefinedAttribute(final String... attributes) {
            super(attributes);
        }

        @Override
        protected boolean isAttributeWritable(final String attributeName) {
            return true;
        }

        @Override
        protected boolean checkValue(final String attrName, final ModelNode attribute, final boolean isWriteAttribute) {
            return !attribute.isDefined();
        }

        @Override
        protected ModelNode correctValue(final ModelNode toResolve, final boolean isWriteAttribute) {
            // Correct by providing a value
            return new ModelNode("token");
        }
    }
}
