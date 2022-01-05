@Test public void shouldNotAuthorizeResourcesFromModelWhenNotInDebugMode(){
  final WroConfiguration config=new WroConfiguration();
  config.setDebug(false);
  Context.set(Context.standaloneContext(),config);
  final String authorizedResourceUri="/authorized.js";
  createSampleModel(authorizedResourceUri);
  Assert.assertFalse(authManager.isAuthorized(authorizedResourceUri));
  Assert.assertFalse(authManager.isAuthorized("/notAuthorized.js"));
}
