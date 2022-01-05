@Test public void encode(){
  RoutingSupport routing=mock(RoutingSupport.class);
  SessionIdentifierCodec codec=new SimpleSessionIdentifierCodec(routing,null);
  String sessionId="session";
  String result=codec.encode(sessionId);
  assertNull(sessionId,null);
  String route="route";
  codec=new SimpleSessionIdentifierCodec(routing,route);
  result=codec.encode(null);
  assertNull(result);
  String encodedSessionId="session.route";
  when(routing.format(sessionId,route)).thenReturn(encodedSessionId);
  result=codec.encode(sessionId);
  assertSame(encodedSessionId,result);
}
