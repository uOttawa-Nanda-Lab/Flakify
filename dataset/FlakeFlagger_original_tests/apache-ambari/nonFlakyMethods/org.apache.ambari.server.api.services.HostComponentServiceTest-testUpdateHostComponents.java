@Test public void testUpdateHostComponents(){
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
  expect(requestFactory.createRequest(eq(httpHeaders),eq("body"),eq(uriInfo),eq(Request.Type.PUT),eq(resourceDef))).andReturn(request);
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultSerializer()).andReturn(resultSerializer);
  expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);
  expect(result.isSynchronous()).andReturn(false).atLeastOnce();
  expect(responseFactory.createResponse(Request.Type.PUT,serializedResult,false)).andReturn(response);
  replay(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
  HostComponentService hostComponentService=new TestHostComponentService(resourceDef,clusterName,hostName,null,requestFactory,responseFactory,requestHandler);
  assertSame(response,hostComponentService.updateHostComponents("body",httpHeaders,uriInfo));
  verify(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
}
