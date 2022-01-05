@Test public void testRenderPlainStringAndSetDefaultContentType(){
  final String toRender="this is just a plain string";
  Result result=Results.ok();
  result.render(toRender);
  resultHandler.handleResult(result,context);
  assertEquals(Result.TEXT_PLAIN,result.getContentType());
}
