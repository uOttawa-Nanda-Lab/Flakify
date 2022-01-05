@Test public void test_AddExtension_Adaptive_ExceptionWhenExistedAdaptive() throws Exception {
  ExtensionLoader<AddExt1> loader=ExtensionLoader.getExtensionLoader(AddExt1.class);
  loader.getAdaptiveExtension();
  try {
    loader.addExtension(null,AddExt1_ManualAdaptive.class);
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Adaptive Extension already existed(Extension interface org.apache.dubbo.common.extension.ext8_add.AddExt1)!"));
  }
}
