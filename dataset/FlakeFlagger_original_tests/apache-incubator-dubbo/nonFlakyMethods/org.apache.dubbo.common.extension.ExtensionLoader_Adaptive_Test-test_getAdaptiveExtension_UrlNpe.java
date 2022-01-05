@Test public void test_getAdaptiveExtension_UrlNpe() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
  try {
    ext.echo(null,"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("url == null",e.getMessage());
  }
}
