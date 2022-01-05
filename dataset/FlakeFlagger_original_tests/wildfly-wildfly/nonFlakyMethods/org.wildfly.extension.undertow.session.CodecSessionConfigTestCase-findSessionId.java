@Test public void findSessionId(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  when(this.config.findSessionId(exchange)).thenReturn(null);
  String result=this.subject.findSessionId(exchange);
  assertNull(result);
  String encodedSessionId="session.route1";
  String sessionId="session";
  when(this.config.findSessionId(exchange)).thenReturn(encodedSessionId);
  when(this.codec.decode(encodedSessionId)).thenReturn(sessionId);
  when(this.codec.encode(sessionId)).thenReturn(encodedSessionId);
  result=this.subject.findSessionId(exchange);
  assertSame(sessionId,result);
  verify(this.config,never()).setSessionId(same(exchange),anyString());
  String reencodedSessionId="session.route2";
  when(this.codec.encode(sessionId)).thenReturn(reencodedSessionId);
  result=this.subject.findSessionId(exchange);
  assertSame(sessionId,result);
  verify(this.config).setSessionId(exchange,reencodedSessionId);
}
