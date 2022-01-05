@Test public void invalidate(){
  BatchContext context=mock(BatchContext.class);
  when(this.batcher.resumeBatch(this.batch)).thenReturn(context);
  this.subject.invalidate();
  verify(context).close();
}
