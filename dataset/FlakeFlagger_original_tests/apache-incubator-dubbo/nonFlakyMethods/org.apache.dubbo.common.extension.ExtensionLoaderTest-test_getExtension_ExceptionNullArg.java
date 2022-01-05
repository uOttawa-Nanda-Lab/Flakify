@Test public void test_getExtension_ExceptionNullArg() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(SimpleExt.class).getExtension(null);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension name == null"));
  }
}
