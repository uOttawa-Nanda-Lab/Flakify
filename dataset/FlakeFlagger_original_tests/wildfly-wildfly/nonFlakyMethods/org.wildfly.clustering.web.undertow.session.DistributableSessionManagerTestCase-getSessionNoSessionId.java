@Test public void getSessionNoSessionId(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  SessionConfig config=mock(SessionConfig.class);
  when(config.findSessionId(exchange)).thenReturn(null);
  io.undertow.server.session.Session sessionAdapter=this.adapter.getSession(exchange,config);
  assertNull(sessionAdapter);
}
