@Test public void test_getExtension() throws Exception {
  assertTrue(ExtensionLoader.getExtensionLoader(CompatibleExt.class).getExtension("impl1") instanceof CompatibleExtImpl1);
  assertTrue(ExtensionLoader.getExtensionLoader(CompatibleExt.class).getExtension("impl2") instanceof CompatibleExtImpl2);
}
