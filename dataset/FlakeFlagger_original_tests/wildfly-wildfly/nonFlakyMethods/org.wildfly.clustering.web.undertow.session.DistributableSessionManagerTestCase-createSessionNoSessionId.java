@Test public void createSessionNoSessionId(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  SessionConfig config=mock(SessionConfig.class);
  Session<LocalSessionContext> session=mock(Session.class);
  String sessionId="session";
  when(this.manager.createIdentifier()).thenReturn(sessionId);
  when(this.manager.createSession(sessionId)).thenReturn(session);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  when(session.getId()).thenReturn(sessionId);
  io.undertow.server.session.Session sessionAdapter=this.adapter.createSession(exchange,config);
  assertNotNull(sessionAdapter);
  verify(this.listener).sessionCreated(sessionAdapter,exchange);
  verify(config).setSessionId(exchange,sessionId);
  verify(batcher).suspendBatch();
  verify(this.statistics).record(sessionAdapter);
  String expected="expected";
  when(session.getId()).thenReturn(expected);
  String result=sessionAdapter.getId();
  assertSame(expected,result);
}
