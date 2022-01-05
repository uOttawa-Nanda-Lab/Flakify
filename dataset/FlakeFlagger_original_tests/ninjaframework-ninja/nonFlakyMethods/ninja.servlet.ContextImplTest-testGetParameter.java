@Test public void testGetParameter(){
  context.init(httpServletRequest,httpServletResponse);
  when(httpServletRequest.getParameter("key")).thenReturn("value");
  assertEquals(null,context.getParameter("key_not_there"));
  assertEquals("defaultValue",context.getParameter("key_not_there","defaultValue"));
  assertEquals("value",context.getParameter("key"));
}
