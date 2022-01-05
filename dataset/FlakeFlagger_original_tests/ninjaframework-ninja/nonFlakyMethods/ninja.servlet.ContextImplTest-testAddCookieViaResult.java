@Test public void testAddCookieViaResult(){
  Cookie cookie=Cookie.builder("cookie","yum").setDomain("domain").build();
  context.init(httpServletRequest,httpServletResponse);
  Result result=Results.html();
  result.addCookie(cookie);
  context.finalizeHeaders(result);
  ArgumentCaptor<javax.servlet.http.Cookie> cookieCaptor=ArgumentCaptor.forClass(javax.servlet.http.Cookie.class);
  verify(httpServletResponse).addCookie(cookieCaptor.capture());
  javax.servlet.http.Cookie resultCookie=cookieCaptor.getValue();
  assertThat(resultCookie.getName(),equalTo("cookie"));
  assertThat(resultCookie.getValue(),equalTo("yum"));
  assertThat(resultCookie.getPath(),equalTo("/"));
  assertThat(resultCookie.getSecure(),equalTo(false));
  assertThat(resultCookie.getMaxAge(),equalTo(-1));
}
