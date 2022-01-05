@Test public void decode(){
  String encodedSessionId="session.route";
  String sessionId="session";
  String route="route";
  when(this.routing.parse(encodedSessionId)).thenReturn(new SimpleImmutableEntry<>(sessionId,route));
  String result=this.codec.decode(encodedSessionId);
  assertSame(sessionId,result);
}
