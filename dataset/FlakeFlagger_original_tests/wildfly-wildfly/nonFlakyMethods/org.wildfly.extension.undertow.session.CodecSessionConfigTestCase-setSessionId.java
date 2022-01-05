@Test public void setSessionId(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  String encodedSessionId="session.route";
  String sessionId="session";
  when(this.codec.encode(sessionId)).thenReturn(encodedSessionId);
  this.subject.setSessionId(exchange,sessionId);
  verify(this.config).setSessionId(exchange,encodedSessionId);
}
