@Test public void testGetAcceptContentType(){
  when(httpServletRequest.getHeader("accept")).thenReturn(null);
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_HTML,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_HTML,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("totally_unknown");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_HTML,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("application/json");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.APPLICATON_JSON,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("text/html, application/json");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_HTML,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("application/xhtml, application/json");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_HTML,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("text/plain");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.TEXT_PLAIN,context.getAcceptContentType());
  when(httpServletRequest.getHeader("accept")).thenReturn("text/plain, application/json");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(Result.APPLICATON_JSON,context.getAcceptContentType());
}
