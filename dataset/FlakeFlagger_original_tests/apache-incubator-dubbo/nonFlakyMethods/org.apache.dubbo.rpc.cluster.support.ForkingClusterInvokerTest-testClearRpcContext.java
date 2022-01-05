@Test public void testClearRpcContext(){
  resetInvokerToException();
  ForkingClusterInvoker<ForkingClusterInvokerTest> invoker=new ForkingClusterInvoker<ForkingClusterInvokerTest>(dic);
  String attachKey="attach";
  String attachValue="value";
  RpcContext.getContext().setAttachment(attachKey,attachValue);
  Map<String,String> attachments=RpcContext.getContext().getAttachments();
  Assert.assertTrue("set attachment failed!",attachments != null && attachments.size() == 1);
  try {
    invoker.invoke(invocation);
    Assert.fail();
  }
 catch (  RpcException expected) {
    Assert.assertTrue("Succeeded to forking invoke provider !",expected.getMessage().contains("Failed to forking invoke provider"));
    assertFalse(expected.getCause() instanceof RpcException);
  }
  Map<String,String> afterInvoke=RpcContext.getContext().getAttachments();
  Assert.assertTrue("clear attachment failed!",afterInvoke != null && afterInvoke.size() == 0);
}
