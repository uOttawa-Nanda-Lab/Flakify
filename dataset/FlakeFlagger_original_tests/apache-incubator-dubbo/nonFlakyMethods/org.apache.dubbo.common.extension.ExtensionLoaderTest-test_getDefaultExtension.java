@Test public void test_getDefaultExtension() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getDefaultExtension();
  assertThat(ext,instanceOf(SimpleExtImpl1.class));
  String name=ExtensionLoader.getExtensionLoader(SimpleExt.class).getDefaultExtensionName();
  assertEquals("impl1",name);
}
