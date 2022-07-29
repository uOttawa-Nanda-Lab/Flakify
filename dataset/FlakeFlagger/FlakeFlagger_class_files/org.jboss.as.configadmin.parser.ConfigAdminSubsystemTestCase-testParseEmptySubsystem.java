/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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
package org.jboss.as.configadmin.parser;

import java.io.IOException;

import org.jboss.as.controller.ProcessType;
import org.jboss.as.controller.RunningMode;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.subsystem.test.AbstractSubsystemBaseTest;
import org.jboss.as.subsystem.test.AdditionalInitialization;
import org.jboss.dmr.ModelNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the {@link ConfigAdminParser}
 *
 * @author Thomas.Diesler@jboss.com
 * @since 10-Jan-2012
 */
public class ConfigAdminSubsystemTestCase extends AbstractSubsystemBaseTest {

    private static final String SUBSYSTEM_XML_1_0_1 =
            "<subsystem xmlns='urn:jboss:domain:configadmin:1.0'>" +
                    "  <!-- Some Comment -->" +
                    "  <configuration pid='Pid1'>" +
                    "    <property name='org.acme.key1' value='val 1'/>" +
                    "  </configuration>" +
                    "  <configuration pid='Pid2'>" +
                    "    <property name='propname' value='propval'/>" +
                    "  </configuration>" +
                    "</subsystem>";

    private static final String SUBSYSTEM_XML_1_0_EXPRESSION =
            "<subsystem xmlns='urn:jboss:domain:configadmin:1.0'>" +
                    "  <!-- Some Comment -->" +
                    "  <configuration pid='Pid1'>" +
                    "    <property name='org.acme.key1' value='${test.exp:val 1}'/>" +
                    "    <property name='propname' value='${test.exp:propval}'/>" +
                    "  </configuration>" +
                    "</subsystem>";

    @Test
    public void testParseEmptySubsystem() throws Exception {
        standardSubsystemTest(null);

    }

    // TODO WFCORE-1353 means this doesn't have to always fail now; consider just deleting this
//    @Override
//    protected void validateDescribeOperation(KernelServices hc, AdditionalInitialization serverInit, ModelNode expectedModel) throws Exception {
//        final ModelNode operation = createDescribeOperation();
//        final ModelNode result = hc.executeOperation(operation);
//        Assert.assertTrue("The subsystem describe operation must fail",
//                result.hasDefined(ModelDescriptionConstants.FAILURE_DESCRIPTION));
//    }
}
