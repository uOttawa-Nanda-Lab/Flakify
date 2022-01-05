@Test public void test_urlHolder_getAdaptiveExtension_ExceptionWhenNameNotProvided() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  URL url=new URL("p1","1.2.3.4",1010,"path1");
  UrlHolder holder=new UrlHolder();
  holder.setUrl(url);
  try {
    ext.echo(holder,"impl1");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Fail to get extension("));
  }
  url=url.addParameter("key1","impl1");
  holder.setUrl(url);
  try {
    ext.echo(holder,"haha");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Fail to get extension(org.apache.dubbo.common.extension.ext2.Ext2) name from url"));
  }
}
