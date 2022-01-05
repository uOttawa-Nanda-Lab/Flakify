@Test public void testNinjaModeHelperWorksWithTestSet(){
  System.setProperty(NinjaConstant.MODE_KEY_NAME,NinjaConstant.MODE_TEST);
  assertEquals(NinjaMode.test,NinjaModeHelper.determineModeFromSystemProperties().get());
  assertEquals(NinjaMode.test,NinjaModeHelper.determineModeFromSystemPropertiesOrDevIfNotSet());
  System.clearProperty(NinjaConstant.MODE_KEY_NAME);
}
