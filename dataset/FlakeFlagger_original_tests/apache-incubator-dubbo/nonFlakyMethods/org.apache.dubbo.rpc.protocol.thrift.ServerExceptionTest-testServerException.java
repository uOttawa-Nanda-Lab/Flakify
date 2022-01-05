@Test(expected=RpcException.class) public void testServerException() throws Exception {
  Assert.assertNotNull(invoker);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("echoString");
  invocation.setParameterTypes(new Class<?>[]{String.class});
  String arg="Hello, World!";
  invocation.setArguments(new Object[]{arg});
  Result result=invoker.invoke(invocation);
  System.out.println(result);
}
