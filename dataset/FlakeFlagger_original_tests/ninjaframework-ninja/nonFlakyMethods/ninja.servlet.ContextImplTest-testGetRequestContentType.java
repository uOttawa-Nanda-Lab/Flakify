@Test public void testGetRequestContentType(){
  String contentType="text/html";
  when(httpServletRequest.getContentType()).thenReturn(contentType);
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(contentType,context.getRequestContentType());
  contentType=null;
  when(httpServletRequest.getContentType()).thenReturn(contentType);
  context.init(httpServletRequest,httpServletResponse);
  assertNull(context.getRequestContentType());
  contentType="text/html; charset=UTF-8";
  when(httpServletRequest.getContentType()).thenReturn(contentType);
  context.init(httpServletRequest,httpServletResponse);
  assertEquals(contentType,context.getRequestContentType());
}
