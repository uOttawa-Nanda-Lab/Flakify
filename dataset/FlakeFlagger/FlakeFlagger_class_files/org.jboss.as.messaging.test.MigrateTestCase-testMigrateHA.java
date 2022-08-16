/*
* JBoss, Home of Professional Open Source.
* Copyright 2015, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.as.messaging.test;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.EXTENSION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP_ADDR;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;
import static org.jboss.as.messaging.MessagingExtension.SUBSYSTEM_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.OperationStepHandler;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.ProcessType;
import org.jboss.as.controller.RunningMode;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.capability.registry.RuntimeCapabilityRegistry;
import org.jboss.as.controller.descriptions.common.ControllerResolver;
import org.jboss.as.controller.extension.ExtensionRegistry;
import org.jboss.as.controller.extension.ExtensionRegistryType;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.Resource;
import org.jboss.as.messaging.MessagingExtension;
import org.jboss.as.subsystem.test.AbstractSubsystemTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.junit.Test;
import org.wildfly.clustering.jgroups.spi.JGroupsDefaultRequirement;

/**
 *
 * @author <a href="http://jmesnil.net/">Jeff Mesnil</a> (c) 2015 Red Hat inc.
 */
public class MigrateTestCase extends AbstractSubsystemTest {

    public static final String MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME = "messaging-activemq";

    @Test public void testMigrateHA() throws Exception{String subsystemXml=readResource("subsystem_migration_ha.xml");newSubsystemAdditionalInitialization additionalInitialization=new newSubsystemAdditionalInitialization();KernelServices services=createKernelServicesBuilder(additionalInitialization).setSubsystemXml(subsystemXml).build();ModelNode model=services.readWholeModel();assertFalse(additionalInitialization.extensionAdded);assertTrue(model.get(SUBSYSTEM,SUBSYSTEM_NAME).isDefined());assertFalse(model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME).isDefined());ModelNode migrateOp=new ModelNode();migrateOp.get(OP).set("migrate");migrateOp.get(OP_ADDR).add(SUBSYSTEM,SUBSYSTEM_NAME);ModelNode response=services.executeOperation(migrateOp);checkOutcome(response);ModelNode warnings=response.get(RESULT,"migration-warnings");assertEquals(warnings.toString(),1 + 1 + 3,warnings.asList().size());model=services.readWholeModel();assertFalse(model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","unmigrated-backup","ha-policy").isDefined());assertFalse(model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","unmigrated-shared-store","ha-policy").isDefined());ModelNode haPolicyForDefaultServer=model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","default","ha-policy","shared-store-master");assertTrue(haPolicyForDefaultServer.isDefined());assertFalse(haPolicyForDefaultServer.get("failback-delay").isDefined());assertEquals(false,haPolicyForDefaultServer.get("failover-on-server-shutdown").asBoolean());ModelNode haPolicyForSharedStoreMasterServer=model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","shared-store-master","ha-policy","shared-store-master");assertTrue(haPolicyForSharedStoreMasterServer.isDefined());assertFalse(haPolicyForSharedStoreMasterServer.get("failback-delay").isDefined());assertEquals("${failover.on.shutdown:true}",haPolicyForSharedStoreMasterServer.get("failover-on-server-shutdown").asString());ModelNode haPolicyForSharedStoreSlaveServer=model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","shared-store-slave","ha-policy","shared-store-slave");assertTrue(haPolicyForSharedStoreSlaveServer.isDefined());assertEquals("${allow.failback.1:false}",haPolicyForSharedStoreSlaveServer.get("allow-failback").asString());assertFalse(haPolicyForSharedStoreSlaveServer.get("failback-delay").isDefined());assertEquals("${failover.on.shutdown.1:true}",haPolicyForSharedStoreSlaveServer.get("failover-on-server-shutdown").asString());ModelNode haPolicyForReplicationMasterServer=model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","replication-master","ha-policy","replication-master");assertTrue(haPolicyForReplicationMasterServer.isDefined());assertEquals("${check.for.live.server:true}",haPolicyForReplicationMasterServer.get("check-for-live-server").asString());assertEquals("${replication.master.group.name:mygroup}",haPolicyForReplicationMasterServer.get("group-name").asString());ModelNode haPolicyForReplicationSlaveServer=model.get(SUBSYSTEM,MESSAGING_ACTIVEMQ_SUBSYSTEM_NAME,"server","replication-slave","ha-policy","replication-slave");assertTrue(haPolicyForReplicationSlaveServer.isDefined());assertEquals("${allow.failback.2:false}",haPolicyForReplicationSlaveServer.get("allow-failback").asString());assertFalse(haPolicyForReplicationSlaveServer.get("failback-delay").isDefined());assertEquals("${max.saved.replicated.journal.size:2}",haPolicyForReplicationSlaveServer.get("max-saved-replicated-journal-size").asString());assertEquals("${replication.master.group.name:mygroup2}",haPolicyForReplicationSlaveServer.get("group-name").asString());}

    private static class newSubsystemAdditionalInitialization extends AdditionalInitialization {

        org.wildfly.extension.messaging.activemq.MessagingExtension newSubsystem = new org.wildfly.extension.messaging.activemq.MessagingExtension();
        boolean extensionAdded = false;

        @Override
        protected void initializeExtraSubystemsAndModel(ExtensionRegistry extensionRegistry, Resource rootResource, ManagementResourceRegistration rootRegistration, RuntimeCapabilityRegistry capabilityRegistry) {
            rootRegistration.registerSubModel(new SimpleResourceDefinition(PathElement.pathElement(EXTENSION),
                    ControllerResolver.getResolver(EXTENSION), new OperationStepHandler() {
                @Override
                public void execute(OperationContext context, ModelNode operation) throws OperationFailedException {
                    extensionAdded = true;
                    newSubsystem.initialize(extensionRegistry.getExtensionContext("org.wildfly.extension.messaging-activemq",
                            rootRegistration, ExtensionRegistryType.SERVER));
                }
            }, null));
            registerCapabilities(capabilityRegistry, JGroupsDefaultRequirement.CHANNEL_FACTORY.getName());
        }

        @Override
        protected ProcessType getProcessType() {
            return ProcessType.HOST_CONTROLLER;
        }

        @Override
        protected RunningMode getRunningMode() {
            return RunningMode.ADMIN_ONLY;
        }
    }
}
