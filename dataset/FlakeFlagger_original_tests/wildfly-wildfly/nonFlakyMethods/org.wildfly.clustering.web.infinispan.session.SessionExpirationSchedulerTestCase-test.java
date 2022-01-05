@Test public void test() throws InterruptedException {
  Batcher<TransactionBatch> batcher=mock(Batcher.class);
  TransactionBatch batch=mock(TransactionBatch.class);
  Remover<String> remover=mock(Remover.class);
  ImmutableSessionMetaData immortalSessionMetaData=mock(ImmutableSessionMetaData.class);
  ImmutableSessionMetaData expiringSessionMetaData=mock(ImmutableSessionMetaData.class);
  ImmutableSessionMetaData canceledSessionMetaData=mock(ImmutableSessionMetaData.class);
  String immortalSessionId="immortal";
  String expiringSessionId="expiring";
  String canceledSessionId="canceled";
  when(batcher.createBatch()).thenReturn(batch);
  when(immortalSessionMetaData.getMaxInactiveInterval()).thenReturn(Duration.ZERO);
  when(expiringSessionMetaData.getMaxInactiveInterval()).thenReturn(Duration.ofMillis(1L));
  when(canceledSessionMetaData.getMaxInactiveInterval()).thenReturn(Duration.ofSeconds(100L));
  Instant now=Instant.now();
  when(expiringSessionMetaData.getLastAccessedTime()).thenReturn(now);
  when(canceledSessionMetaData.getLastAccessedTime()).thenReturn(now);
  try (Scheduler scheduler=new SessionExpirationScheduler(batcher,remover)){
    scheduler.schedule(immortalSessionId,immortalSessionMetaData);
    scheduler.schedule(canceledSessionId,canceledSessionMetaData);
    scheduler.schedule(expiringSessionId,expiringSessionMetaData);
    TimeUnit.SECONDS.sleep(1L);
    scheduler.cancel(canceledSessionId);
    scheduler.schedule(canceledSessionId,canceledSessionMetaData);
  }
   verify(remover,never()).remove(immortalSessionId);
  verify(remover).remove(expiringSessionId);
  verify(remover,never()).remove(canceledSessionId);
  verify(batch).close();
}
