@Test public void testAssetsController404() throws Exception {
  AssetsController assetsController=new AssetsController(httpCacheToolkit,mimeTypes,ninjaProperties);
  when(contextRenerable.getRequestPath()).thenReturn("notAvailable");
  Result result2=assetsController.serve(null);
  Renderable renderable=(Renderable)result2.getRenderable();
  Result result=Results.ok();
  renderable.render(contextRenerable,result);
  verify(contextRenerable).finalizeHeadersWithoutFlashAndSessionCookie(resultCaptor.capture());
  assertTrue(resultCaptor.getValue().getStatusCode() == Result.SC_404_NOT_FOUND);
}
