@Test public void test_AddExtension_ExceptionWhenExistedExtension() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getExtension("impl1");
  try {
    ExtensionLoader.getExtensionLoader(AddExt1.class).addExtension("impl1",AddExt1_ManualAdd1.class);
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Extension name impl1 already existed(Extension interface org.apache.dubbo.common.extension.ext8_add.AddExt1)!"));
  }
}
