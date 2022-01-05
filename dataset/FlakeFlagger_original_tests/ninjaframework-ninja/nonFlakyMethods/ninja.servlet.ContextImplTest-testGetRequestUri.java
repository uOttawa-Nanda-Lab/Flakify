@Test public void testGetRequestUri(){
  when(httpServletRequest.getRequestURI()).thenReturn("/index");
  context.init(httpServletRequest,httpServletResponse);
  assertEquals("/index",context.getRequestUri());
}
