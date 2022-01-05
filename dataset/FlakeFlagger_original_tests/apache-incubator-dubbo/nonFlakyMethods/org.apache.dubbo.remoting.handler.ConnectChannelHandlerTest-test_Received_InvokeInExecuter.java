@Test(expected=ExecutionException.class) public void test_Received_InvokeInExecuter() throws RemotingException {
  handler=new ConnectionOrderedChannelHandler(new BizChannelHander(false),url);
  ThreadPoolExecutor executor=(ThreadPoolExecutor)getField(handler,"SHARED_EXECUTOR",1);
  executor.shutdown();
  executor=(ThreadPoolExecutor)getField(handler,"executor",1);
  executor.shutdown();
  handler.received(new MockedChannel(),"");
}
