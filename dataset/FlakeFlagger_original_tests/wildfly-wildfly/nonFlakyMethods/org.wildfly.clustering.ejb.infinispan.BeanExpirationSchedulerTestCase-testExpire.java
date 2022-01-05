@Test public void testExpire() throws InterruptedException {
  Batcher<TransactionBatch> batcher=mock(Batcher.class);
  TransactionBatch batch=mock(TransactionBatch.class);
  BeanRemover<String,Object> remover=mock(BeanRemover.class);
  ExpirationConfiguration<Object> config=mock(ExpirationConfiguration.class);
  RemoveListener<Object> listener=mock(RemoveListener.class);
  String beanId="expiring";
  when(config.getExecutor()).thenReturn(Executors.newSingleThreadScheduledExecutor());
  when(batcher.createBatch()).thenReturn(batch);
  when(config.getTimeout()).thenReturn(new Time(1,TimeUnit.MILLISECONDS));
  when(config.getRemoveListener()).thenReturn(listener);
  try (Scheduler<String> scheduler=new BeanExpirationScheduler<>(batcher,remover,config)){
    scheduler.schedule(beanId);
    Thread.sleep(1000);
  }
   verify(remover).remove(beanId,listener);
  verify(batch).close();
}
