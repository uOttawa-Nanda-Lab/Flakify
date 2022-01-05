@Test public void testCancel() throws InterruptedException {
  Batcher<TransactionBatch> batcher=mock(Batcher.class);
  BeanRemover<String,Object> remover=mock(BeanRemover.class);
  ExpirationConfiguration<Object> config=mock(ExpirationConfiguration.class);
  RemoveListener<Object> listener=mock(RemoveListener.class);
  String beanId="canceled";
  when(config.getExecutor()).thenReturn(Executors.newSingleThreadScheduledExecutor());
  when(config.getTimeout()).thenReturn(new Time(1,TimeUnit.MINUTES));
  when(config.getRemoveListener()).thenReturn(listener);
  try (Scheduler<String> scheduler=new BeanExpirationScheduler<>(batcher,remover,config)){
    scheduler.schedule(beanId);
    Thread.sleep(1000);
    scheduler.cancel(beanId);
    scheduler.schedule(beanId);
  }
   verify(remover,never()).remove(beanId,listener);
  verify(batcher,never()).createBatch();
}
