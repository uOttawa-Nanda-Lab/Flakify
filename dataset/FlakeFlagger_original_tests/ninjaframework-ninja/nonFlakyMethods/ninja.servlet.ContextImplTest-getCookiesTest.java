@Test public void getCookiesTest(){
  javax.servlet.http.Cookie servletCookie1=new javax.servlet.http.Cookie("contextCookie1","theValue");
  javax.servlet.http.Cookie servletCookie2=new javax.servlet.http.Cookie("contextCookie2","theValue");
  javax.servlet.http.Cookie[] servletCookiesEmpty={};
  javax.servlet.http.Cookie[] servletCookies={servletCookie1,servletCookie2};
  when(httpServletRequest.getCookies()).thenReturn(servletCookiesEmpty);
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(0,context.getCookies().size());
  when(httpServletRequest.getCookies()).thenReturn(servletCookies);
  assertEquals(2,context.getCookies().size());
}
