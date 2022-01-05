@Test public void getExecutor2() throws Exception {
  URL url=URL.valueOf("dubbo://10.20.130.230:20880/context/path?" + Constants.QUEUES_KEY + "=2");
  ThreadPool threadPool=new EagerThreadPool();
  ThreadPoolExecutor executor=(ThreadPoolExecutor)threadPool.getExecutor(url);
  assertThat(executor.getQueue().remainingCapacity(),is(2));
}
