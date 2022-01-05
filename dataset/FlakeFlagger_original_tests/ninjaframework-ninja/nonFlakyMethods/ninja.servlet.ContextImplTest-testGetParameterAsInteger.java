@Test public void testGetParameterAsInteger(){
  context.init(httpServletRequest,httpServletResponse);
  when(httpServletRequest.getParameter("key")).thenReturn("1");
  assertEquals(null,context.getParameterAsInteger("key_not_there"));
  assertEquals(new Integer(100),context.getParameterAsInteger("key_not_there",100));
  assertEquals(new Integer(1),context.getParameterAsInteger("key"));
}
