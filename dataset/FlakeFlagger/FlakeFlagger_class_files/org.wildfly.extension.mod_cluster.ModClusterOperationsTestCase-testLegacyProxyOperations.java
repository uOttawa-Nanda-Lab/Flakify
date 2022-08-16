/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2018, Red Hat, Inc., and individual contributors
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
package org.wildfly.extension.mod_cluster;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DEPRECATED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.FAILED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.FAILURE_DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.NAME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OUTCOME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.READ_OPERATION_DESCRIPTION_OPERATION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.READ_OPERATION_NAMES_OPERATION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUCCESS;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.WRITE_ATTRIBUTE_OPERATION;
import static org.jboss.as.domain.management.ModelDescriptionConstants.VALUE;

import java.io.IOException;

import org.jboss.as.clustering.controller.Attribute;
import org.jboss.as.clustering.controller.CommonUnaryRequirement;
import org.jboss.as.clustering.controller.Operations;
import org.jboss.as.clustering.subsystem.AdditionalInitialization;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.operations.common.Util;
import org.jboss.as.subsystem.test.AbstractSubsystemTest;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for testing individual management operations, especially useless and convoluted legacy operations.
 *
 * @author Radoslav Husar
 */
@SuppressWarnings("SameParameterValue")
public class ModClusterOperationsTestCase extends AbstractSubsystemTest {

    private final String PROXY_NAME = "default";

    /**
	 * Tests that legacy proxy operations are registered at the subsystem level.
	 */@Test public void testLegacyProxyOperations() throws Exception{KernelServices services=this.buildKernelServices();ModelNode op=Util.createOperation(READ_OPERATION_NAMES_OPERATION,getSubsystemAddress());ModelNode result=services.executeOperation(op);Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());for (ProxyOperation proxyOperation:ProxyOperation.values()){String operationName=proxyOperation.getDefinition().getName();Assert.assertTrue(String.format("'%s' legacy operation is not registered",operationName),result.get(RESULT).asList().contains(new ModelNode(operationName)));ModelNode rodOp=Util.createOperation(READ_OPERATION_DESCRIPTION_OPERATION,getSubsystemAddress());rodOp.get(NAME).set(operationName);ModelNode rodResult=services.executeOperation(rodOp);Assert.assertEquals(rodResult.get(FAILURE_DESCRIPTION).asString(),SUCCESS,rodResult.get(OUTCOME).asString());Assert.assertTrue(rodResult.get(RESULT).hasDefined(DEPRECATED));}}

    

    // Addresses

    private static PathAddress getSubsystemAddress() {
        return PathAddress.pathAddress(ModClusterSubsystemResourceDefinition.PATH);
    }


    private static PathAddress getProxyAddress(String proxyName) {
        return getSubsystemAddress().append(ProxyConfigurationResourceDefinition.pathElement(proxyName));
    }

    private static PathAddress getSimpleLoadProviderAddress(String proxyName) {
        return getProxyAddress(proxyName).append(SimpleLoadProviderResourceDefinition.PATH);
    }

    private static PathAddress getLegacyModClusterConfigAddress() {
        return getSubsystemAddress().append(ProxyConfigurationResourceDefinition.LEGACY_PATH);
    }

    private static PathAddress getLegacyModClusterConfigDynamicLoadProviderAddress() {
        return getLegacyModClusterConfigAddress().append(DynamicLoadProviderResourceDefinition.LEGACY_PATH);
    }

    private static PathAddress getLegacyModClusterConfigLoadMetricAddress(String metric) {
        return getLegacyModClusterConfigDynamicLoadProviderAddress().append(LoadMetricResourceDefinition.pathElement(metric));
    }


    // Operations

    private static ModelNode createLegacyModClusterConfigWriteAttributeOperation(Attribute attribute, ModelNode value) {
        return Operations.createWriteAttributeOperation(getLegacyModClusterConfigAddress(), attribute, value);
    }

    private static ModelNode createLegacyModClusterConfigLoadMetricWriteAttributeOperation(String metric, Attribute attribute, ModelNode value) {
        return Operations.createWriteAttributeOperation(getLegacyModClusterConfigLoadMetricAddress(metric), attribute, value);
    }

    private static ModelNode createLegacyAddMetricOperation(String type) {
        ModelNode operation = Util.createOperation("add-metric", getLegacyModClusterConfigAddress());
        operation.get("type").set(type);
        return operation;
    }

    private static ModelNode createLegacyRemoveMetricOperation(String type) {
        ModelNode operation = Util.createOperation("remove-metric", getLegacyModClusterConfigAddress());
        operation.get("type").set(type);
        return operation;
    }

    // Setup

    private String getSubsystemXml() throws IOException {
        return readResource("subsystem-transform.xml");
    }

    private KernelServices buildKernelServices() throws Exception {
        return createKernelServicesBuilder(new AdditionalInitialization().require(CommonUnaryRequirement.OUTBOUND_SOCKET_BINDING, "proxy1")).setSubsystemXml(this.getSubsystemXml()).build();
    }
}