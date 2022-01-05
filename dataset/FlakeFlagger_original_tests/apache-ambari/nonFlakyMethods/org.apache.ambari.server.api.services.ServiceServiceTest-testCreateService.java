@Test public void testCreateService(){
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
  String serviceName="serviceName";
  expect(requestFactory.createRequest(eq(httpHeaders),eq("body"),eq(uriInfo),eq(Request.Type.POST),eq(resourceDef))).andReturn(request);
  expect(result.isSynchronous()).andReturn(false).atLeastOnce();
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultSerializer()).andReturn(resultSerializer);
  expect(resultSerializer.serialize(result,uriInfo)).andReturn(serializedResult);
  expect(responseFactory.createResponse(Request.Type.POST,serializedResult,false)).andReturn(response);
  replay(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
  ServiceService hostService=new TestServiceService(resourceDef,clusterName,serviceName,requestFactory,responseFactory,requestHandler);
  assertSame(response,hostService.createService("body",httpHeaders,uriInfo,serviceName));
  verify(resourceDef,resultSerializer,requestFactory,responseFactory,request,requestHandler,result,response,httpHeaders,uriInfo);
}
