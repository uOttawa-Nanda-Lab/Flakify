/** 
 * Make sure the correct character encoding is set before the inputStream is returned. Otherwise we might get strange default encodings from the servlet engine.
 */
@Test public void testGetInputStreamEnforcingOfCorrectEncoding() throws Exception {
  context.init(httpServletRequest,httpServletResponse);
  context.getInputStream();
  verify(httpServletRequest).setCharacterEncoding(anyString());
}
