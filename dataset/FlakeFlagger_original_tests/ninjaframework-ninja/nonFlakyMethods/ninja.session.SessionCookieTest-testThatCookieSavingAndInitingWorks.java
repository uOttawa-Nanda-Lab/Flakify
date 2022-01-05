@Test public void testThatCookieSavingAndInitingWorks(){
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.put("key1","value1");
  sessionCookie.put("key2","value2");
  sessionCookie.put("key3","value3");
  sessionCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  Cookie newSessionCookie=Cookie.builder(cookieCaptor.getValue().getName(),cookieCaptor.getValue().getValue()).build();
  when(context.getCookie(cookieCaptor.getValue().getName())).thenReturn(newSessionCookie);
  SessionCookie sessionCookie2=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie2.init(context);
  assertEquals("value1",sessionCookie2.get("key1"));
  assertEquals("value2",sessionCookie2.get("key2"));
  assertEquals("value3",sessionCookie2.get("key3"));
}
