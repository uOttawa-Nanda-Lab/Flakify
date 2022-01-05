@Test public void testGetRequestPathWorksAsExpectedWithContext(){
  when(httpServletRequest.getContextPath()).thenReturn("/my/funky/prefix");
  when(httpServletRequest.getRequestURI()).thenReturn("/my/funky/prefix/myapp/is/here");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals("/myapp/is/here",context.getRequestPath());
}
