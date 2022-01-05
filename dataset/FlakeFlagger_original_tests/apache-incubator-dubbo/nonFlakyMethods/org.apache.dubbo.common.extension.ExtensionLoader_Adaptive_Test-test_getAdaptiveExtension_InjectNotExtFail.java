@Test public void test_getAdaptiveExtension_InjectNotExtFail() throws Exception {
  Ext6 ext=ExtensionLoader.getExtensionLoader(Ext6.class).getExtension("impl2");
  Ext6Impl2 impl=(Ext6Impl2)ext;
  assertNull(impl.getList());
}
