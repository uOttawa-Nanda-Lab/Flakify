@Test public void testAssetsController304NotModified() throws Exception {
  AssetsController assetsController=new AssetsController(httpCacheToolkit,mimeTypes,ninjaProperties);
  when(contextRenerable.getRequestPath()).thenReturn("/assets/testasset.txt");
  Result result2=assetsController.serve(null);
  Renderable renderable=(Renderable)result2.getRenderable();
  Result result=Results.ok();
  result.status(Result.SC_304_NOT_MODIFIED);
  renderable.render(contextRenerable,result);
  verify(httpCacheToolkit).addEtag(Mockito.eq(contextRenerable),Mockito.eq(result),Mockito.anyLong());
  verify(contextRenerable).finalizeHeadersWithoutFlashAndSessionCookie(resultCaptor.capture());
  assertEquals(Result.SC_304_NOT_MODIFIED,resultCaptor.getValue().getStatusCode());
}
