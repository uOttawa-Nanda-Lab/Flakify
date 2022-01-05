/** 
 * Make sure the correct character encoding is set before the reader is returned. Otherwise we might get strange default encodings from the servlet engine.
 */
@Test public void testGetReaderEnforcingOfCorrectEncoding() throws Exception {
  context.init(httpServletRequest,httpServletResponse);
  context.getReader();
  verify(httpServletRequest).setCharacterEncoding(anyString());
}
