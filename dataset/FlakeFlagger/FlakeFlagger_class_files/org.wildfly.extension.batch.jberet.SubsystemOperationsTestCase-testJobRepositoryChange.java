/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

package org.wildfly.extension.batch.jberet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jboss.as.controller.capability.registry.RuntimeCapabilityRegistry;
import org.jboss.as.controller.client.Operation;
import org.jboss.as.controller.client.helpers.Operations.CompositeOperationBuilder;
import org.jboss.as.controller.extension.ExtensionRegistry;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.Resource;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.as.subsystem.test.SubsystemOperations;
import org.jboss.dmr.ModelNode;
import org.junit.Test;
import org.wildfly.extension.batch.jberet.job.repository.InMemoryJobRepositoryDefinition;

public class SubsystemOperationsTestCase extends AbstractBatchTestCase {

    @Test public void testJobRepositoryChange() throws Exception{final KernelServices kernelServices=boot();final CompositeOperationBuilder compositeOp=CompositeOperationBuilder.create();final ModelNode address=createAddress(InMemoryJobRepositoryDefinition.NAME,"new-job-repo");compositeOp.addStep(SubsystemOperations.createAddOperation(address));compositeOp.addStep(SubsystemOperations.createWriteAttributeOperation(createAddress(null),"default-thread-pool","new-job-repo"));executeOperation(kernelServices,compositeOp.build());}
}
