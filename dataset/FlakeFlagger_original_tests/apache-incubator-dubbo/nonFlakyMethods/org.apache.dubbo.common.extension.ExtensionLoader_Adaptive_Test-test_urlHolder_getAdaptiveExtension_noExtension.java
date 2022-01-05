@Test public void test_urlHolder_getAdaptiveExtension_noExtension() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  URL url=new URL("p1","1.2.3.4",1010,"path1");
  UrlHolder holder=new UrlHolder();
  holder.setUrl(url);
  try {
    ext.echo(holder,"haha");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Fail to get extension("));
  }
  url=url.addParameter("ext2","XXX");
  holder.setUrl(url);
  try {
    ext.echo(holder,"haha");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("No such extension"));
  }
}
