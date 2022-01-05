@Test public void testInvokeGreaterActives(){
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&actives=1&timeout=1");
  final Invoker<ActiveLimitFilterTest> invoker=new BlockMyInvoker<ActiveLimitFilterTest>(url,100);
  final Invocation invocation=new MockInvocation();
  final CountDownLatch latch=new CountDownLatch(1);
  for (int i=0; i < 100; i++) {
    Thread thread=new Thread(new Runnable(){
      public void run(){
        try {
          latch.await();
        }
 catch (        InterruptedException e) {
          e.printStackTrace();
        }
        for (int i=0; i < 100; i++) {
          try {
            activeLimitFilter.invoke(invoker,invocation);
          }
 catch (          RpcException expected) {
            count++;
          }
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
  assertNotSame(0,count);
}
