@Test public void test_replaceExtension_Adaptive() throws Exception {
  ExtensionLoader<AddExt3> loader=ExtensionLoader.getExtensionLoader(AddExt3.class);
  AddExt3 adaptive=loader.getAdaptiveExtension();
  assertFalse(adaptive instanceof AddExt3_ManualAdaptive);
  loader.replaceExtension(null,AddExt3_ManualAdaptive.class);
  adaptive=loader.getAdaptiveExtension();
  assertTrue(adaptive instanceof AddExt3_ManualAdaptive);
}
