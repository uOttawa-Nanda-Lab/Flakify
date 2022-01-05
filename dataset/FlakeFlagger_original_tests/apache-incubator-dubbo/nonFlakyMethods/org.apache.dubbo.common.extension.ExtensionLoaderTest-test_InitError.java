@Test public void test_InitError() throws Exception {
  ExtensionLoader<InitErrorExt> loader=ExtensionLoader.getExtensionLoader(InitErrorExt.class);
  loader.getExtension("ok");
  try {
    loader.getExtension("error");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Failed to load extension class(interface: interface org.apache.dubbo.common.extension.ext7.InitErrorExt"));
    assertThat(expected.getCause(),instanceOf(ExceptionInInitializerError.class));
  }
}
