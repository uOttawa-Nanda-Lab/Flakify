@Test public void testNinjaModeHelperWorksWithNoModeSet(){
  assertEquals(false,NinjaModeHelper.determineModeFromSystemProperties().isPresent());
  assertEquals(NinjaMode.dev,NinjaModeHelper.determineModeFromSystemPropertiesOrDevIfNotSet());
}
