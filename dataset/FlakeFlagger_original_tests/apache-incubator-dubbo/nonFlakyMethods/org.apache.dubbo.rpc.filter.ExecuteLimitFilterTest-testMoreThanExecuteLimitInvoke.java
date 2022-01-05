@Test public void testMoreThanExecuteLimitInvoke() throws Exception {
  int maxExecute=10;
  int totalExecute=20;
  final AtomicInteger failed=new AtomicInteger(0);
  final Invocation invocation=Mockito.mock(Invocation.class);
  when(invocation.getMethodName()).thenReturn("testMoreThanExecuteLimitInvoke");
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&executes=" + maxExecute);
  final Invoker<ExecuteLimitFilter> invoker=new BlockMyInvoker<ExecuteLimitFilter>(url,1000);
  final CountDownLatch latch=new CountDownLatch(1);
  for (int i=0; i < totalExecute; i++) {
    Thread thread=new Thread(new Runnable(){
      public void run(){
        try {
          latch.await();
        }
 catch (        InterruptedException e) {
          e.printStackTrace();
        }
        try {
          executeLimitFilter.invoke(invoker,invocation);
        }
 catch (        RpcException expected) {
          failed.incrementAndGet();
        }
      }
    }
);
    thread.start();
  }
  latch.countDown();
  try {
    Thread.sleep(1000);
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  Assert.assertEquals(totalExecute - maxExecute,failed.get());
}
