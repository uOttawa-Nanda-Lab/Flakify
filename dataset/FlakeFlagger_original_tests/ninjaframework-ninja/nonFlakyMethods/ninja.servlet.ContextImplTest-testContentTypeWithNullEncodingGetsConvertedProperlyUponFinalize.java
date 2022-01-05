@Test public void testContentTypeWithNullEncodingGetsConvertedProperlyUponFinalize(){
  context.init(httpServletRequest,httpServletResponse);
  Result result=Results.json();
  result.charset(null);
  context.finalizeHeaders(result);
  verify(httpServletResponse).setCharacterEncoding(NinjaConstant.UTF_8);
}
