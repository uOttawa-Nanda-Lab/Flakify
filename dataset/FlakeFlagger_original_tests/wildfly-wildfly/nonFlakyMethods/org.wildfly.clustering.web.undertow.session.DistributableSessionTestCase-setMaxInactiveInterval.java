@Test public void setMaxInactiveInterval(){
  int interval=3600;
  this.validate(session -> session.setMaxInactiveInterval(interval));
  SessionManager<LocalSessionContext,Batch> manager=mock(SessionManager.class);
  Batcher<Batch> batcher=mock(Batcher.class);
  BatchContext context=mock(BatchContext.class);
  SessionMetaData metaData=mock(SessionMetaData.class);
  when(this.manager.getSessionManager()).thenReturn(manager);
  when(manager.getBatcher()).thenReturn(batcher);
  when(batcher.resumeBatch(this.batch)).thenReturn(context);
  when(this.session.getMetaData()).thenReturn(metaData);
  this.adapter.setMaxInactiveInterval(interval);
  verify(metaData).setMaxInactiveInterval(Duration.ofSeconds(interval));
  verify(context).close();
}
