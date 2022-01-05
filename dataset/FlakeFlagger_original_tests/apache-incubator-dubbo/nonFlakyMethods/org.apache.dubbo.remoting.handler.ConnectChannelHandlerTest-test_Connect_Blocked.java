@Test public void test_Connect_Blocked() throws RemotingException {
  handler=new ConnectionOrderedChannelHandler(new BizChannelHander(false),url);
  ThreadPoolExecutor executor=(ThreadPoolExecutor)getField(handler,"connectionExecutor",1);
  Assert.assertEquals(1,executor.getMaximumPoolSize());
  int runs=20;
  int taskCount=runs * 2;
  for (int i=0; i < runs; i++) {
    handler.connected(new MockedChannel());
    handler.disconnected(new MockedChannel());
    Assert.assertTrue(executor.getActiveCount() + " must <=1",executor.getActiveCount() <= 1);
  }
  Assert.assertEquals(taskCount - 1,executor.getQueue().size());
  for (int i=0; i < taskCount; i++) {
    if (executor.getCompletedTaskCount() < taskCount) {
      sleep(100);
    }
  }
  Assert.assertEquals(taskCount,executor.getCompletedTaskCount());
}
