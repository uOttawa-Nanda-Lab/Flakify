@Test public void getCreationTime(){
  this.validate(session -> session.getCreationTime());
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionMetaData metaData=mock(SessionMetaData.class);
  Instant now=Instant.now();
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.getCreationTime()).thenReturn(now);
  long result=this.adapter.getCreationTime();
  assertEquals(now.toEpochMilli(),result);
  verify(context).close();
}
