@Test public void getSessionNotExists(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  SessionConfig config=mock(SessionConfig.class);
  String sessionId="session";
  when(config.findSessionId(exchange)).thenReturn(sessionId);
  when(this.manager.findSession(sessionId)).thenReturn(null);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  io.undertow.server.session.Session sessionAdapter=this.adapter.getSession(exchange,config);
  assertNull(sessionAdapter);
  verify(batch).close();
  verify(batcher,never()).suspendBatch();
}
