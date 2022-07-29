/*
 * Copyright 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.as.ee.subsystem;

import java.io.IOException;

import org.jboss.as.controller.RunningMode;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.as.controller.client.helpers.Operations;
import org.jboss.as.controller.client.helpers.Operations.CompositeOperationBuilder;
import org.jboss.as.subsystem.test.AbstractSubsystemBaseTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.ControllerInitializer;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;
import org.wildfly.common.cpu.ProcessorInfo;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class EeOperationsTestCase extends AbstractSubsystemBaseTest {

    @Test public void testManagedExecutorOperations() throws Exception{final KernelServices kernelServices=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();final ModelNode address=Operations.createAddress(ClientConstants.SUBSYSTEM,EeExtension.SUBSYSTEM_NAME,"managed-executor-service","default");ModelNode op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"queue-length",Integer.MAX_VALUE)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",5)).build().getOperation();executeForSuccess(kernelServices,op);op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"max-threads",5)).addStep(Operations.createWriteAttributeOperation(address,"queue-length",10)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",0)).build().getOperation();executeForSuccess(kernelServices,op);op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"core-threads",4)).addStep(Operations.createWriteAttributeOperation(address,"max-threads",4)).build().getOperation();executeForSuccess(kernelServices,op);}

    private ModelNode executeForSuccess(final KernelServices kernelServices, final ModelNode op) {
        final ModelNode result = kernelServices.executeOperation(op);
        if (!Operations.isSuccessfulOutcome(result)) {
            Assert.fail(Operations.getFailureDescription(result).asString());
        }
        return result;
    }

    class EeInitializer extends ControllerInitializer {
        public EeInitializer() {
            addSystemProperty("test.queue-length", "0");
            addSystemProperty("test.core-threads", "0");
        }
    }
}
