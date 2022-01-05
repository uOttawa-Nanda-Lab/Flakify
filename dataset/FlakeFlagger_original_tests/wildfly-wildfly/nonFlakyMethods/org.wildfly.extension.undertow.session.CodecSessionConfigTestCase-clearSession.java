@Test public void clearSession(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  String encodedSessionId="session.route";
  String sessionId="session";
  when(this.codec.encode(sessionId)).thenReturn(encodedSessionId);
  this.subject.clearSession(exchange,sessionId);
  verify(this.config).clearSession(exchange,encodedSessionId);
}
