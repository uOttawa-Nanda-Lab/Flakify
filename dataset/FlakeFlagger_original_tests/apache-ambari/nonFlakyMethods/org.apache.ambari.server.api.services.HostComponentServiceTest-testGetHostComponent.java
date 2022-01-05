@Test public void testGetHostComponent(){
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
  String hostName="hostName";
  String hostComponentName="hostComponentName";
  expect(requestFactory.createRequest(eq(httpHeaders),isNull(String.class),eq(uriInfo),eq(Request.Type.GET),eq(resourceDef))).andReturn(request);
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultSerializer()).andReturn(resultSerializer);
  expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);
  expect(result.isSynchronous()).andReturn(true).atLeastOnce();
  expect(responseFactory.createResponse(Request.Type.GET,serializedResult,true)).andReturn(response);
  replay(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
  HostComponentService hostComponentService=new TestHostComponentService(resourceDef,clusterName,hostName,hostComponentName,requestFactory,responseFactory,requestHandler);
  assertSame(response,hostComponentService.getHostComponent(httpHeaders,uriInfo,hostComponentName));
  verify(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
}
