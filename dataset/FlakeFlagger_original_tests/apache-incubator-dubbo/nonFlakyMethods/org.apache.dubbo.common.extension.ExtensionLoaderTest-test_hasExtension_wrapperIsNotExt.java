@Test public void test_hasExtension_wrapperIsNotExt() throws Exception {
  assertTrue(ExtensionLoader.getExtensionLoader(WrappedExt.class).hasExtension("impl1"));
  assertFalse(ExtensionLoader.getExtensionLoader(WrappedExt.class).hasExtension("impl1,impl2"));
  assertFalse(ExtensionLoader.getExtensionLoader(WrappedExt.class).hasExtension("xxx"));
  assertFalse(ExtensionLoader.getExtensionLoader(WrappedExt.class).hasExtension("wrapper1"));
  try {
    ExtensionLoader.getExtensionLoader(WrappedExt.class).hasExtension(null);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension name == null"));
  }
}
