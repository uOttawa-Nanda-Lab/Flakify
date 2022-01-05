@Test public void testImmortal() throws InterruptedException {
  Batcher<TransactionBatch> batcher=mock(Batcher.class);
  BeanRemover<String,Object> remover=mock(BeanRemover.class);
  ExpirationConfiguration<Object> config=mock(ExpirationConfiguration.class);
  RemoveListener<Object> listener=mock(RemoveListener.class);
  String beanId="immortal";
  when(config.getExecutor()).thenReturn(Executors.newSingleThreadScheduledExecutor());
  when(config.getTimeout()).thenReturn(new Time(-1,TimeUnit.SECONDS));
  when(config.getRemoveListener()).thenReturn(listener);
  try (Scheduler<String> scheduler=new BeanExpirationScheduler<>(batcher,remover,config)){
    scheduler.schedule(beanId);
    Thread.sleep(1000);
  }
   verify(batcher,never()).createBatch();
  verify(remover,never()).remove(beanId,listener);
}
