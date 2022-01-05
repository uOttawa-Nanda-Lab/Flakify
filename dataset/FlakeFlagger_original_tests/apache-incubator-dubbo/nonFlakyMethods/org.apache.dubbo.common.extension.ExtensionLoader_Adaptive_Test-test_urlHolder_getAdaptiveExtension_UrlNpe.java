@Test public void test_urlHolder_getAdaptiveExtension_UrlNpe() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  try {
    ext.echo(null,"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument == null",e.getMessage());
  }
  try {
    ext.echo(new UrlHolder(),"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument getUrl() == null",e.getMessage());
  }
}
