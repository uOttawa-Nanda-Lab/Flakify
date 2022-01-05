@Test public void getCookieTest(){
  javax.servlet.http.Cookie servletCookie1=new javax.servlet.http.Cookie("contextCookie1","theValue1");
  javax.servlet.http.Cookie servletCookie2=new javax.servlet.http.Cookie("contextCookie2","theValue2");
  javax.servlet.http.Cookie[] servletCookies={servletCookie1,servletCookie2};
  when(httpServletRequest.getCookies()).thenReturn(servletCookies);
  context.init(httpServletRequest,httpServletResponse);
  ninja.Cookie doesNotExist=context.getCookie("doesNotExist");
  assertNull(doesNotExist);
  ninja.Cookie cookie1=context.getCookie("contextCookie1");
  assertEquals(cookie1.getName(),"contextCookie1");
  assertEquals(cookie1.getValue(),"theValue1");
  ninja.Cookie cookie2=context.getCookie("contextCookie2");
  assertEquals(cookie2.getName(),"contextCookie2");
  assertEquals(cookie2.getValue(),"theValue2");
}
