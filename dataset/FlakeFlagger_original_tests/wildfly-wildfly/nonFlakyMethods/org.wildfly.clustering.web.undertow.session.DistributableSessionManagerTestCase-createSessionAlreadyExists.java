@Test public void createSessionAlreadyExists(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  SessionConfig config=mock(SessionConfig.class);
  String sessionId="session";
  when(config.findSessionId(exchange)).thenReturn(sessionId);
  when(this.manager.createSession(sessionId)).thenReturn(null);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  IllegalStateException exception=null;
  try {
    this.adapter.createSession(exchange,config);
  }
 catch (  IllegalStateException e) {
    exception=e;
  }
  assertNotNull(exception);
  verify(batch).discard();
  verify(batch).close();
}
