@Test public void hasCookieTest(){
  javax.servlet.http.Cookie servletCookie1=new javax.servlet.http.Cookie("contextCookie1","theValue1");
  javax.servlet.http.Cookie servletCookie2=new javax.servlet.http.Cookie("contextCookie2","theValue2");
  javax.servlet.http.Cookie[] servletCookies={servletCookie1,servletCookie2};
  when(httpServletRequest.getCookies()).thenReturn(servletCookies);
  context.init(httpServletRequest,httpServletResponse);
  assertFalse(context.hasCookie("doesNotExist"));
  assertTrue(context.hasCookie("contextCookie1"));
  assertTrue(context.hasCookie("contextCookie2"));
}
