@Test public void getExecutor2() throws Exception {
  URL url=URL.valueOf("dubbo://10.20.130.230:20880/context/path?" + Constants.QUEUES_KEY + "=1");
  ThreadPool threadPool=new LimitedThreadPool();
  ThreadPoolExecutor executor=(ThreadPoolExecutor)threadPool.getExecutor(url);
  assertThat(executor.getQueue(),Matchers.<BlockingQueue<Runnable>>instanceOf(LinkedBlockingQueue.class));
}
