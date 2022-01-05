public void testProcessEngineDenialOfServiceAttackUsingUnsafeXmlTest() throws InterruptedException {
  MyRunnable runnable=new MyRunnable(repositoryService);
  Thread thread=new Thread(runnable);
  thread.start();
  long waitTime=60000L;
  thread.join(waitTime);
  assertTrue(runnable.finished);
}
