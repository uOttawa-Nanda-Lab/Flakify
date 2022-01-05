@Test public void test_replaceExtension_ExceptionWhenNotExistedExtension() throws Exception {
  AddExt1 ext=ExtensionLoader.getExtensionLoader(AddExt1.class).getExtension("impl1");
  try {
    ExtensionLoader.getExtensionLoader(AddExt1.class).replaceExtension("NotExistedExtension",AddExt1_ManualAdd1.class);
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Extension name NotExistedExtension not existed(Extension interface org.apache.dubbo.common.extension.ext8_add.AddExt1)"));
  }
}
