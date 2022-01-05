@Test public void testSessionCookieDelete(){
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  final String key="mykey";
  final String value="myvalue";
  sessionCookie.put(key,value);
  assertEquals(value,sessionCookie.get(key));
  assertEquals(value,sessionCookie.remove(key));
  assertNull(sessionCookie.get(key));
}
