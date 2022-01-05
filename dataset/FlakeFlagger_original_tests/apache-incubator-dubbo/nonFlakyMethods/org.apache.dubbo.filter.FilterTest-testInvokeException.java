@Test public void testInvokeException(){
  try {
    Invoker<FilterTest> invoker=new MyInvoker<FilterTest>(null);
    Invocation invocation=new MockInvocation("aa");
    myFilter.invoke(invoker,invocation);
    fail();
  }
 catch (  RpcException e) {
    Assert.assertTrue(e.getMessage().contains("arg0 illegal"));
  }
}
