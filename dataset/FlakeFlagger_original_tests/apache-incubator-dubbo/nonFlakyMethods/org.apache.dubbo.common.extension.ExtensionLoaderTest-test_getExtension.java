@Test public void test_getExtension() throws Exception {
  assertTrue(ExtensionLoader.getExtensionLoader(SimpleExt.class).getExtension("impl1") instanceof SimpleExtImpl1);
  assertTrue(ExtensionLoader.getExtensionLoader(SimpleExt.class).getExtension("impl2") instanceof SimpleExtImpl2);
}
