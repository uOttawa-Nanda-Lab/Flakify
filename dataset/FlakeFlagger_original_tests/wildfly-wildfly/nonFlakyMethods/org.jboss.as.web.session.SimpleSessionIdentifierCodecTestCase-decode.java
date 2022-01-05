@Test public void decode(){
  RoutingSupport routing=mock(RoutingSupport.class);
  String route="route";
  SessionIdentifierCodec codec=new SimpleSessionIdentifierCodec(routing,route);
  String result=codec.decode(null);
  assertNull(result);
  String sessionId="session";
  when(routing.parse(sessionId)).thenReturn(new SimpleImmutableEntry<String,String>(sessionId,null));
  result=codec.decode(sessionId);
  assertSame(sessionId,result);
  String encodedSessionId="session.route";
  when(routing.parse(encodedSessionId)).thenReturn(new SimpleImmutableEntry<>(sessionId,route));
  result=codec.decode(encodedSessionId);
  assertSame(sessionId,result);
}
