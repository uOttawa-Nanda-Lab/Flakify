@Test public void getExecutor1() throws Exception {
  URL url=URL.valueOf("dubbo://10.20.130.230:20880/context/path?" + Constants.THREAD_NAME_KEY + "=demo&"+ Constants.CORE_THREADS_KEY+ "=1&"+ Constants.THREADS_KEY+ "=2&"+ Constants.ALIVE_KEY+ "=1000&"+ Constants.QUEUES_KEY+ "=0");
  ThreadPool threadPool=new CachedThreadPool();
  ThreadPoolExecutor executor=(ThreadPoolExecutor)threadPool.getExecutor(url);
  assertThat(executor.getCorePoolSize(),is(1));
  assertThat(executor.getMaximumPoolSize(),is(2));
  assertThat(executor.getQueue(),Matchers.<BlockingQueue<Runnable>>instanceOf(SynchronousQueue.class));
  assertThat(executor.getRejectedExecutionHandler(),Matchers.<RejectedExecutionHandler>instanceOf(AbortPolicyWithReport.class));
  final CountDownLatch latch=new CountDownLatch(1);
  executor.execute(new Runnable(){
    @Override public void run(){
      Thread thread=Thread.currentThread();
      assertThat(thread,instanceOf(InternalThread.class));
      assertThat(thread.getName(),startsWith("demo"));
      latch.countDown();
    }
  }
);
  latch.await();
  assertThat(latch.getCount(),is(0L));
}
