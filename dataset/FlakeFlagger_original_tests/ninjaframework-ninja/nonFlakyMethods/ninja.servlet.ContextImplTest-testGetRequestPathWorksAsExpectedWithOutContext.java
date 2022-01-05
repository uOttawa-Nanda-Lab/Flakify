@Test public void testGetRequestPathWorksAsExpectedWithOutContext(){
  when(httpServletRequest.getContextPath()).thenReturn("");
  when(httpServletRequest.getRequestURI()).thenReturn("/index");
  when(httpServletRequest.getRequestURI()).thenReturn("/myapp/is/here");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals("/myapp/is/here",context.getRequestPath());
}
