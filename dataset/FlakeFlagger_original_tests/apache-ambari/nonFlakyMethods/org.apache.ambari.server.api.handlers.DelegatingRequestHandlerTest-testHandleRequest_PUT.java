@Test public void testHandleRequest_PUT(){
  Request request=createStrictMock(Request.class);
  RequestHandlerFactory factory=createStrictMock(RequestHandlerFactory.class);
  RequestHandler requestHandler=createStrictMock(RequestHandler.class);
  Result result=createStrictMock(Result.class);
  ResultPostProcessor resultProcessor=createStrictMock(ResultPostProcessor.class);
  expect(request.getRequestType()).andReturn(Request.Type.POST);
  expect(factory.getRequestHandler(Request.Type.POST)).andReturn(requestHandler);
  expect(requestHandler.handleRequest(request)).andReturn(result);
  expect(request.getResultPostProcessor()).andReturn(resultProcessor);
  resultProcessor.process(result);
  replay(request,factory,requestHandler,result,resultProcessor);
  RequestHandler delegatingRequestHandler=new TestDelegatingRequestHandler(factory);
  assertSame(result,delegatingRequestHandler.handleRequest(request));
  verify(request,factory,requestHandler,result,resultProcessor);
}
