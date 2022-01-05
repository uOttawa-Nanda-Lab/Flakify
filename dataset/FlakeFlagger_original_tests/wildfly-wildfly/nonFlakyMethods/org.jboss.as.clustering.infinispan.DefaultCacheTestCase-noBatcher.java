@Test public void noBatcher(){
  when(this.batcherFactory.createBatcher(this.cache)).thenReturn(null);
  AdvancedCache<Object,Object> subject=new DefaultCache<>(this.manager,this.batcherFactory,this.cache);
  boolean started=subject.startBatch();
  assertFalse(started);
  verify(this.cache,never()).startBatch();
  subject.endBatch(false);
  verify(this.cache,never()).endBatch(false);
}
