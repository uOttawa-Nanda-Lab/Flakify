@Test public void batcher(){
  @SuppressWarnings("unchecked") Batcher<TransactionBatch> batcher=mock(Batcher.class);
  TransactionBatch batch=mock(TransactionBatch.class);
  when(this.batcherFactory.createBatcher(this.cache)).thenReturn(batcher);
  AdvancedCache<Object,Object> subject=new DefaultCache<>(this.manager,this.batcherFactory,this.cache);
  when(batcher.createBatch()).thenReturn(batch);
  boolean started=subject.startBatch();
  assertTrue(started);
  started=subject.startBatch();
  assertFalse(started);
  subject.endBatch(true);
  verify(batch,never()).discard();
  verify(batch).close();
  reset(batch);
  subject.endBatch(true);
  verify(batch,never()).close();
  verify(batch,never()).discard();
  started=subject.startBatch();
  assertTrue(started);
  subject.endBatch(false);
  verify(batch).discard();
  verify(batch).close();
  reset(batch);
  subject.endBatch(true);
  verify(batch,never()).close();
  verify(batch,never()).discard();
}
