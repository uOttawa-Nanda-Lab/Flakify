@Test public void test_hasExtension() throws Exception {
  assertTrue(ExtensionLoader.getExtensionLoader(SimpleExt.class).hasExtension("impl1"));
  assertFalse(ExtensionLoader.getExtensionLoader(SimpleExt.class).hasExtension("impl1,impl2"));
  assertFalse(ExtensionLoader.getExtensionLoader(SimpleExt.class).hasExtension("xxx"));
  try {
    ExtensionLoader.getExtensionLoader(SimpleExt.class).hasExtension(null);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension name == null"));
  }
}
