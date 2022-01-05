@SuppressWarnings("unchecked") @Test public void testConvertToRunTimeException(){
  ExceptionFilter exceptionFilter=new ExceptionFilter();
  RpcInvocation invocation=new RpcInvocation("sayHello",new Class<?>[]{String.class},new Object[]{"world"});
  RpcResult rpcResult=new RpcResult();
  rpcResult.setException(new HessianException("hessian"));
  Invoker<DemoService> invoker=mock(Invoker.class);
  when(invoker.invoke(invocation)).thenReturn(rpcResult);
  when(invoker.getInterface()).thenReturn(DemoService.class);
  Result newResult=exceptionFilter.invoke(invoker,invocation);
  Assert.assertFalse(newResult.getException() instanceof HessianException);
  Assert.assertEquals(newResult.getException().getClass(),RuntimeException.class);
  Assert.assertEquals(newResult.getException().getMessage(),StringUtils.toString(rpcResult.getException()));
}
