@Test public void testInvokeException(){
  resetInvokerToException();
  ForkingClusterInvoker<ForkingClusterInvokerTest> invoker=new ForkingClusterInvoker<ForkingClusterInvokerTest>(dic);
  try {
    invoker.invoke(invocation);
    Assert.fail();
  }
 catch (  RpcException expected) {
    Assert.assertTrue(expected.getMessage().contains("Failed to forking invoke provider"));
    assertFalse(expected.getCause() instanceof RpcException);
  }
}
