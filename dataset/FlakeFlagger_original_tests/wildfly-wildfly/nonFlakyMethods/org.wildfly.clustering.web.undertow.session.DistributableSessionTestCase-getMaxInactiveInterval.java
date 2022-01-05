@Test public void getMaxInactiveInterval(){
  this.validate(session -> session.getMaxInactiveInterval());
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionMetaData metaData=mock(SessionMetaData.class);
  long expected=3600L;
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.getMaxInactiveInterval()).thenReturn(Duration.ofSeconds(expected));
  long result=this.adapter.getMaxInactiveInterval();
  assertEquals(expected,result);
  verify(context).close();
}
