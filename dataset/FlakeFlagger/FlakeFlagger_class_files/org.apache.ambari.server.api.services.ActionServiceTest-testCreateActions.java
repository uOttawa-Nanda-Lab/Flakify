/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.api.services;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isNull;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.ambari.server.api.handlers.RequestHandler;
import org.apache.ambari.server.api.resources.ResourceInstance;
import org.apache.ambari.server.api.services.serializers.ResultSerializer;
import org.junit.Test;

public class ActionServiceTest {

  @Test public void testCreateActions(){ResourceInstance resource=createStrictMock(ResourceInstance.class);ResultSerializer resultSerializer=createStrictMock(ResultSerializer.class);Object serializedResult=new Object();RequestFactory requestFactory=createStrictMock(RequestFactory.class);ResponseFactory responseFactory=createStrictMock(ResponseFactory.class);Request request=createNiceMock(Request.class);RequestHandler requestHandler=createStrictMock(RequestHandler.class);Result result=createStrictMock(Result.class);Response response=createStrictMock(Response.class);HttpHeaders httpHeaders=createNiceMock(HttpHeaders.class);UriInfo uriInfo=createNiceMock(UriInfo.class);String clusterName="clusterName";String serviceName="HBASE";String body="{ \"actionName\":\"compaction\"," + "\"parameters\":{ \"key1\":\"value1\", \"key2\":\"value2\" } }";expect(requestFactory.createRequest(eq(httpHeaders),eq(body),eq(uriInfo),eq(Request.Type.POST),eq(resource))).andReturn(request);expect(requestHandler.handleRequest(request)).andReturn(result);expect(request.getResultSerializer()).andReturn(resultSerializer);expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);expect(result.isSynchronous()).andReturn(false).atLeastOnce();expect(responseFactory.createResponse(Request.Type.POST,serializedResult,false)).andReturn(response);replay(resource,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);TestActionService hostService=new TestActionService(resource,clusterName,requestFactory,responseFactory,requestHandler,serviceName);assertSame(response,hostService.createActions(body,httpHeaders,uriInfo));verify(resource,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);}

  private class TestActionService extends ActionService {
    private ResourceInstance m_resourceDef;
    private String m_clusterId;
    private String m_serviceId;
    private RequestFactory m_requestFactory;
    private ResponseFactory m_responseFactory;
    private RequestHandler m_requestHandler;

    public TestActionService(ResourceInstance resourceDef,
        String clusterName, RequestFactory requestFactory,
        ResponseFactory responseFactory, RequestHandler handler,
        String serviceName) {
      super(clusterName, serviceName);
      m_resourceDef = resourceDef;
      m_clusterId = clusterName;
      m_serviceId = serviceName;
      m_requestFactory = requestFactory;
      m_responseFactory = responseFactory;
      m_requestHandler = handler;
    }

    @Override
    ResourceInstance createActionResource(String clusterName, String serviceName, String actionName) {
      assertEquals(m_clusterId, clusterName);
      assertEquals(m_serviceId, serviceName);
      return m_resourceDef;
    }

    @Override
    RequestFactory getRequestFactory() {
      return m_requestFactory;
    }

    @Override
    ResponseFactory getResponseFactory() {
      return m_responseFactory;
    }

    @Override
    RequestHandler getRequestHandler() {
      return m_requestHandler;
    }
  }
}
