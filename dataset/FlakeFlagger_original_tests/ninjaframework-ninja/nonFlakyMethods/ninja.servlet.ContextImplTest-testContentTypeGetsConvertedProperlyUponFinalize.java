@Test public void testContentTypeGetsConvertedProperlyUponFinalize(){
  context.init(httpServletRequest,httpServletResponse);
  Result result=Results.json();
  context.finalizeHeaders(result);
  verify(httpServletResponse).setCharacterEncoding(result.getCharset());
  verify(httpServletResponse).setContentType(result.getContentType());
}
