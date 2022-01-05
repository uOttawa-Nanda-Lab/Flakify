@Test public void getSessionInvalidCharacters(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  SessionConfig config=mock(SessionConfig.class);
  String sessionId="session+";
  when(config.findSessionId(exchange)).thenReturn(sessionId);
  io.undertow.server.session.Session sessionAdapter=this.adapter.getSession(exchange,config);
  assertNull(sessionAdapter);
  sessionAdapter=this.adapter.getSession(sessionId);
  assertNull(sessionAdapter);
  verify(this.manager,never()).findSession(sessionId);
}
