@Test public void close(){
  BatchContext context=mock(BatchContext.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  this.subject.close();
  verify(this.batch).close();
  verify(context).close();
  reset(this.batch);
  this.subject.close();
  verify(this.batch,never()).close();
}
