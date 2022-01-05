@Test public void testCreateHosts(){
  ResourceInstance resourceDef=createStrictMock(ResourceInstance.class);
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
  String body="[ " + "{\"Hosts\" : {" + "            \"cluster_name\" : \"mycluster\","+ "            \"host_name\" : \"host1\""+ "          }"+ "},"+ "{\"Hosts\" : {"+ "            \"cluster_name\" : \"mycluster\","+ "            \"host_name\" : \"host2\""+ "          }"+ "},"+ "{\"Hosts\" : {"+ "            \"cluster_name\" : \"mycluster\","+ "            \"host_name\" : \"host3\""+ "          }"+ "}]";
  expect(requestFactory.createRequest(eq(httpHeaders),eq(body),eq(uriInfo),eq(Request.Type.POST),eq(resourceDef))).andReturn(request);
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultSerializer()).andReturn(resultSerializer);
  expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);
  expect(result.isSynchronous()).andReturn(false).atLeastOnce();
  expect(responseFactory.createResponse(Request.Type.POST,serializedResult,false)).andReturn(response);
  replay(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
  HostService hostService=new TestHostService(resourceDef,clusterName,null,requestFactory,responseFactory,requestHandler);
  assertSame(response,hostService.createHosts(body,httpHeaders,uriInfo));
  verify(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
}
