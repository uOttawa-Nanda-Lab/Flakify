@Test public void test_AddExtension_Adaptive() throws Exception {
  ExtensionLoader<AddExt2> loader=ExtensionLoader.getExtensionLoader(AddExt2.class);
  loader.addExtension(null,AddExt2_ManualAdaptive.class);
  AddExt2 adaptive=loader.getAdaptiveExtension();
  assertTrue(adaptive instanceof AddExt2_ManualAdaptive);
}
