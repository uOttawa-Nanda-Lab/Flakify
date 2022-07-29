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
 * 2110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.test.integration.web.session;

import static org.wildfly.extension.undertow.Constants.ATTRIBUTE;
import static org.wildfly.extension.undertow.Constants.GET_SESSION_ATTRIBUTE;
import static org.wildfly.extension.undertow.Constants.GET_SESSION_CREATION_TIME;
import static org.wildfly.extension.undertow.Constants.GET_SESSION_CREATION_TIME_MILLIS;
import static org.wildfly.extension.undertow.Constants.GET_SESSION_LAST_ACCESSED_TIME;
import static org.wildfly.extension.undertow.Constants.GET_SESSION_LAST_ACCESSED_TIME_MILLIS;
import static org.wildfly.extension.undertow.Constants.INVALIDATE_SESSION;
import static org.wildfly.extension.undertow.Constants.LIST_SESSIONS;
import static org.wildfly.extension.undertow.Constants.LIST_SESSION_ATTRIBUTES;
import static org.wildfly.extension.undertow.Constants.LIST_SESSION_ATTRIBUTE_NAMES;
import static org.wildfly.extension.undertow.Constants.SESSION_ID;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.as.arquillian.container.ManagementClient;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.test.shared.TestSuiteEnvironment;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 */
@RunWith(Arquillian.class)
@RunAsClient
public class SessionManagementTestCase {

    @ArquillianResource
    public ManagementClient managementClient;


    @Test public void testSessionManagementOperationsNegative() throws Exception{try (CloseableHttpClient client=HttpClients.createDefault()){ModelNode operation=new ModelNode();operation.get(ModelDescriptionConstants.OP_ADDR).set(PathAddress.parseCLIStyleAddress("/deployment=management.war/subsystem=undertow").toModelNode());String sessionId="non-existing-id";operation.get(SESSION_ID).set(sessionId);negativeTestsCheck(operation,LIST_SESSION_ATTRIBUTE_NAMES);negativeTestsCheck(operation,LIST_SESSION_ATTRIBUTES);operation.get(ATTRIBUTE).set("val");negativeTestsCheck(operation,GET_SESSION_ATTRIBUTE);executeOperation(operation,INVALIDATE_SESSION);HttpGet get=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/management/SessionPersistenceServlet");HttpResponse res=client.execute(get);sessionId=null;for (Header cookie:res.getHeaders("Set-Cookie")){if (cookie.getValue().startsWith("JSESSIONID=")){sessionId=cookie.getValue().split("=")[1].split("\\.")[0];break;}}operation.get(SESSION_ID).set(sessionId);operation.get(ATTRIBUTE).set("non-existing");ModelNode opRes=executeOperation(operation,GET_SESSION_ATTRIBUTE);Assert.assertEquals("undefined",opRes.get(ModelDescriptionConstants.RESULT).asString());executeOperation(operation,INVALIDATE_SESSION);} }

    private ModelNode executeOperation(ModelNode operation, String operationName) throws IOException {
        operation.get(ModelDescriptionConstants.OP).set(operationName);

        ModelNode opRes = managementClient.getControllerClient().execute(operation);
        Assert.assertEquals(opRes.toString(), "success", opRes.get(ModelDescriptionConstants.OUTCOME).asString());

        return opRes;
    }

    private void negativeTestsCheck(ModelNode operation, String operationName) throws IOException {
        operation.get(ModelDescriptionConstants.OP).set(operationName);

        ModelNode opRes = managementClient.getControllerClient().execute(operation);
        Assert.assertEquals(opRes.toString(), "failed", opRes.get(ModelDescriptionConstants.OUTCOME).asString());
        String failDesc = opRes.get(ModelDescriptionConstants.FAILURE_DESCRIPTION).asString();
        Assert.assertTrue(failDesc.contains("WFLYUT0100"));
    }
}
