@Test public void testRenderRaw() throws Exception {
  String stringToRender="{\"user\" : \"john@woo.com\"}";
  Result result=Results.json().renderRaw(stringToRender);
  ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
  when(context.finalizeHeaders(result)).thenReturn(responseStreams);
  when(responseStreams.getOutputStream()).thenReturn(byteArrayOutputStream);
  Renderable renderable=(Renderable)result.getRenderable();
  renderable.render(context,result);
  verify(context).finalizeHeaders(result);
  assertEquals(byteArrayOutputStream.toString(),stringToRender);
  assertEquals(Result.APPLICATON_JSON,result.getContentType());
}
