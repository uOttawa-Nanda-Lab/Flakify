@Test public void testAssetsControllerNormalOperationModifiedNoCaching() throws Exception {
  AssetsController assetsController=new AssetsController(httpCacheToolkit,mimeTypes,ninjaProperties);
  Result result=Results.ok();
  when(contextRenerable.getRequestPath()).thenReturn("/assets/testasset.txt");
  when(mimeTypes.getContentType(Mockito.eq(contextRenerable),Mockito.anyString())).thenReturn("mimetype");
  when(contextRenerable.finalizeHeadersWithoutFlashAndSessionCookie(Mockito.eq(result))).thenReturn(responseStreams);
  ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
  when(responseStreams.getOutputStream()).thenReturn(byteArrayOutputStream);
  Result result2=assetsController.serve(null);
  Renderable renderable=(Renderable)result2.getRenderable();
  renderable.render(contextRenerable,result);
  verify(httpCacheToolkit).addEtag(Mockito.eq(contextRenerable),Mockito.eq(result),Mockito.anyLong());
  verify(contextRenerable).finalizeHeadersWithoutFlashAndSessionCookie(resultCaptor.capture());
  assertEquals(Result.SC_200_OK,resultCaptor.getValue().getStatusCode());
  assertEquals("mimetype",result.getContentType());
  assertEquals("testasset",byteArrayOutputStream.toString());
}
