@Test public void test_getExtensionLoader_NotInterface() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(ExtensionLoaderTest.class);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension type(class org.apache.dubbo.common.extension.ExtensionLoaderTest) is not interface"));
  }
}
