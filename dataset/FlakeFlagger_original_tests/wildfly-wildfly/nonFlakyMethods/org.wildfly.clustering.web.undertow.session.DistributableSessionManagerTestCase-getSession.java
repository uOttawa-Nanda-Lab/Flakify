@Test public void getSession(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  Batcher<Batch> batcher=mock(Batcher.class);
  Batch batch=mock(Batch.class);
  SessionConfig config=mock(SessionConfig.class);
  Session<LocalSessionContext> session=mock(Session.class);
  String sessionId="session";
  when(config.findSessionId(exchange)).thenReturn(sessionId);
  when(this.manager.findSession(sessionId)).thenReturn(session);
  when(this.manager.getBatcher()).thenReturn(batcher);
  when(batcher.createBatch()).thenReturn(batch);
  when(session.getId()).thenReturn(sessionId);
  io.undertow.server.session.Session sessionAdapter=this.adapter.getSession(exchange,config);
  assertNotNull(sessionAdapter);
  verifyZeroInteractions(this.statistics);
  verify(batcher).suspendBatch();
  String expected="expected";
  when(session.getId()).thenReturn(expected);
  String result=sessionAdapter.getId();
  assertSame(expected,result);
}
