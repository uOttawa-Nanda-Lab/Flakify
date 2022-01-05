@Test public void testCreateConfiguration(){
  ResourceInstance resourceInstance=createStrictMock(ResourceInstance.class);
  ResultSerializer resultSerializer=createStrictMock(ResultSerializer.class);
  Object serializedResult=new Object();
  RequestFactory requestFactory=createStrictMock(RequestFactory.class);
  ResponseFactory responseFactory=createStrictMock(ResponseFactory.class);
  Request request=createNiceMock(Request.class);
  RequestHandler requestHandler=createStrictMock(RequestHandler.class);
  Result result=createStrictMock(Result.class);
  Response response=createStrictMock(Response.class);
  HttpHeaders httpHeaders=createNiceMock(HttpHeaders.class);
  UriInfo uriInfo=createNiceMock(UriInfo.class);
  String clusterName="clusterName";
  String body="{ \"type\":\"hdfs-site\", \"tag\":\"my-new-tag\"," + "\"properties\":{ \"key1\":\"value1\", \"key2\":\"value2\" } }";
  expect(requestFactory.createRequest(eq(httpHeaders),eq(body),eq(uriInfo),eq(Request.Type.POST),eq(resourceInstance))).andReturn(request);
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultSerializer()).andReturn(resultSerializer);
  expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);
  expect(result.isSynchronous()).andReturn(false).atLeastOnce();
  expect(responseFactory.createResponse(Request.Type.POST,serializedResult,false)).andReturn(response);
  replay(resourceInstance,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
  ConfigurationService hostService=new TestConfigurationService(resourceInstance,clusterName,requestFactory,responseFactory,requestHandler);
  assertSame(response,hostService.createConfigurations(body,httpHeaders,uriInfo));
  verify(resourceInstance,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
}
