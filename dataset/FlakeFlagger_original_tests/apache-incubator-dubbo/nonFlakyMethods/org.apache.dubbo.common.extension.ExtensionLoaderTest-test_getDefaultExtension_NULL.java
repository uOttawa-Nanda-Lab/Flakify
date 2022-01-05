@Test public void test_getDefaultExtension_NULL() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getDefaultExtension();
  assertNull(ext);
  String name=ExtensionLoader.getExtensionLoader(Ext2.class).getDefaultExtensionName();
  assertNull(name);
}
