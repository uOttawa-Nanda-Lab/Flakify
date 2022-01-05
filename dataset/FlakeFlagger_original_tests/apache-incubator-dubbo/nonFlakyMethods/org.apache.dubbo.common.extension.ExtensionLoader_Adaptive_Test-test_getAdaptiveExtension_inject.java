@Test public void test_getAdaptiveExtension_inject() throws Exception {
  LogUtil.start();
  Ext6 ext=ExtensionLoader.getExtensionLoader(Ext6.class).getAdaptiveExtension();
  URL url=new URL("p1","1.2.3.4",1010,"path1");
  url=url.addParameters("ext6","impl1");
  assertEquals("Ext6Impl1-echo-Ext1Impl1-echo",ext.echo(url,"ha"));
  Assert.assertTrue("can not find error.",LogUtil.checkNoError());
  LogUtil.stop();
  url=url.addParameters("simple.ext","impl2");
  assertEquals("Ext6Impl1-echo-Ext1Impl2-echo",ext.echo(url,"ha"));
}
