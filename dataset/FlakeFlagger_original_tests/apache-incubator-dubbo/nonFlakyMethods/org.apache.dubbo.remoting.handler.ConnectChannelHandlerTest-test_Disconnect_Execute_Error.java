@Test(expected=ExecutionException.class) public void test_Disconnect_Execute_Error() throws RemotingException {
  handler=new ConnectionOrderedChannelHandler(new BizChannelHander(false),url);
  ThreadPoolExecutor executor=(ThreadPoolExecutor)getField(handler,"connectionExecutor",1);
  executor.shutdown();
  handler.disconnected(new MockedChannel());
}
