@Test public void shouldAuthorizeResourcesFromModelWhenInDebugMode(){
  final WroConfiguration config=new WroConfiguration();
  config.setDebug(true);
  Context.set(Context.standaloneContext(),config);
  final String authorizedResourceUri="/authorized.js";
  createSampleModel(authorizedResourceUri);
  Assert.assertTrue(authManager.isAuthorized(authorizedResourceUri));
  Assert.assertFalse(authManager.isAuthorized("/notAuthorized.js"));
}
