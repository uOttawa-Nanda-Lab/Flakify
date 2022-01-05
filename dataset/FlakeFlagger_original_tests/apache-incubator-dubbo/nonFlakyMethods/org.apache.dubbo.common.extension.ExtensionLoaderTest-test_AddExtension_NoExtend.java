@Test public void test_AddExtension_NoExtend() throws Exception {
  ExtensionLoader.getExtensionLoader(Ext9Empty.class).addExtension("ext9",Ext9EmptyImpl.class);
  Ext9Empty ext=ExtensionLoader.getExtensionLoader(Ext9Empty.class).getExtension("ext9");
  assertThat(ext,instanceOf(Ext9Empty.class));
  assertEquals("ext9",ExtensionLoader.getExtensionLoader(Ext9Empty.class).getExtensionName(Ext9EmptyImpl.class));
}
