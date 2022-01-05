@Test public void test_getExtension_ExceptionNoExtension() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(SimpleExt.class).getExtension("XXX");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("No such extension org.apache.dubbo.common.extension.ext1.SimpleExt by name XXX"));
  }
}
