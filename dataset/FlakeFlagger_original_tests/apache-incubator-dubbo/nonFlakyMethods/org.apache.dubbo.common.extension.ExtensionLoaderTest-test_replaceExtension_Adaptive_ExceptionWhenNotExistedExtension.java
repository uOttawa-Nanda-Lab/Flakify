@Test public void test_replaceExtension_Adaptive_ExceptionWhenNotExistedExtension() throws Exception {
  ExtensionLoader<AddExt4> loader=ExtensionLoader.getExtensionLoader(AddExt4.class);
  try {
    loader.replaceExtension(null,AddExt4_ManualAdaptive.class);
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Adaptive Extension not existed(Extension interface org.apache.dubbo.common.extension.ext8_add.AddExt4)"));
  }
}
