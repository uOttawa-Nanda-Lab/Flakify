@Test public void testNinjaModeHelperWorksWithDevSet(){
  System.setProperty(NinjaConstant.MODE_KEY_NAME,NinjaConstant.MODE_DEV);
  assertEquals(NinjaMode.dev,NinjaModeHelper.determineModeFromSystemProperties().get());
  assertEquals(NinjaMode.dev,NinjaModeHelper.determineModeFromSystemPropertiesOrDevIfNotSet());
  System.clearProperty(NinjaConstant.MODE_KEY_NAME);
}
