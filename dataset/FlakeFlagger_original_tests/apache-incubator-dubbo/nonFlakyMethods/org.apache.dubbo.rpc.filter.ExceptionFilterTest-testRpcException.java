@SuppressWarnings("unchecked") @Test public void testRpcException(){
  Logger logger=mock(Logger.class);
  RpcContext.getContext().setRemoteAddress("127.0.0.1",1234);
  RpcException exception=new RpcException("TestRpcException");
  ExceptionFilter exceptionFilter=new ExceptionFilter(logger);
  RpcInvocation invocation=new RpcInvocation("sayHello",new Class<?>[]{String.class},new Object[]{"world"});
  Invoker<DemoService> invoker=mock(Invoker.class);
  given(invoker.getInterface()).willReturn(DemoService.class);
  given(invoker.invoke(eq(invocation))).willThrow(exception);
  try {
    exceptionFilter.invoke(invoker,invocation);
  }
 catch (  RpcException e) {
    assertEquals("TestRpcException",e.getMessage());
  }
  Mockito.verify(logger).error(eq("Got unchecked and undeclared exception which called by 127.0.0.1. service: " + DemoService.class.getName() + ", method: sayHello, exception: "+ RpcException.class.getName()+ ": TestRpcException"),eq(exception));
  RpcContext.removeContext();
}
