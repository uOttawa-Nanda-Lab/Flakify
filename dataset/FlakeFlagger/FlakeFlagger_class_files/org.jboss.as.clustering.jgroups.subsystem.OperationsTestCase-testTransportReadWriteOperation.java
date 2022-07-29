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
package org.jboss.as.clustering.jgroups.subsystem;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.FAILURE_DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OUTCOME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUCCESS;

import org.jboss.as.controller.ExpressionResolver;
import org.jboss.as.subsystem.test.KernelServices;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for testing individual management operations.
 *
 * @author Richard Achmatowicz (c) 2011 Red Hat Inc.
 */
public class OperationsTestCase extends OperationTestCaseBase {

    /**
	 * Tests access to transport attributes
	 */@Test public void testTransportReadWriteOperation() throws Exception{KernelServices services=this.buildKernelServices();ModelNode result=services.executeOperation(getTransportReadOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());Assert.assertEquals("rack1",ExpressionResolver.TEST_RESOLVER.resolveExpressions(result.get(RESULT)).asString());result=services.executeOperation(getTransportWriteOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK,"new-rack"));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());result=services.executeOperation(getTransportReadOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK));Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());Assert.assertEquals("new-rack",result.get(RESULT).asString());}
}