@Test public void testHandleRequest_GET(){
  Request request=createStrictMock(Request.class);
  RequestHandlerFactory factory=createStrictMock(RequestHandlerFactory.class);
  RequestHandler readRequestHandler=createStrictMock(RequestHandler.class);
  Result result=createStrictMock(Result.class);
  ResultPostProcessor resultProcessor=createStrictMock(ResultPostProcessor.class);
  expect(request.getRequestType()).andReturn(Request.Type.GET);
  expect(factory.getRequestHandler(Request.Type.GET)).andReturn(readRequestHandler);
  expect(readRequestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultPostProcessor()).andReturn(resultProcessor);
  resultProcessor.process(result);
  replay(request,factory,readRequestHandler,result,resultProcessor);
  RequestHandler delegatingRequestHandler=new TestDelegatingRequestHandler(factory);
  assertSame(result,delegatingRequestHandler.handleRequest(request));
  verify(request,factory,readRequestHandler,result,resultProcessor);
}
