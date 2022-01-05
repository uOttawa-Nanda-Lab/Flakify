@Test public void encode(){
  String sessionId="session";
  when(this.locator.locate(sessionId)).thenReturn(null);
  String result=this.codec.encode(sessionId);
  assertSame(sessionId,result);
  String route="route";
  String encodedSessionId="session.route";
  when(this.locator.locate(sessionId)).thenReturn(route);
  when(this.routing.format(sessionId,route)).thenReturn(encodedSessionId);
  result=this.codec.encode(sessionId);
  assertSame(encodedSessionId,result);
}
