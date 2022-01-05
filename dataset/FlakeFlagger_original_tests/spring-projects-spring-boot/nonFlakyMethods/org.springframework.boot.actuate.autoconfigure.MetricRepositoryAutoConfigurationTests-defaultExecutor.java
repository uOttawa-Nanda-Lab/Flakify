@Test public void defaultExecutor() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MetricRepositoryAutoConfiguration.class);
  ExecutorSubscribableChannel channel=context.getBean(ExecutorSubscribableChannel.class);
  ThreadPoolTaskExecutor executor=(ThreadPoolTaskExecutor)channel.getExecutor();
  context.close();
  assertTrue(executor.getThreadPoolExecutor().isShutdown());
}
