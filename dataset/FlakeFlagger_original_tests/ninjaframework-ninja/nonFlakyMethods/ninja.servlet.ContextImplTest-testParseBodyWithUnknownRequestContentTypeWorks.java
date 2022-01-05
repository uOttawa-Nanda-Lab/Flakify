/** 
 * We get an conetnt type that does not match any registered parsers. This must also return null safely.
 */
@Test public void testParseBodyWithUnknownRequestContentTypeWorks(){
  when(httpServletRequest.getContentType()).thenReturn("application/UNKNOWN");
  context.init(httpServletRequest,httpServletResponse);
  Object o=context.parseBody(Dummy.class);
  assertNull(o);
}
