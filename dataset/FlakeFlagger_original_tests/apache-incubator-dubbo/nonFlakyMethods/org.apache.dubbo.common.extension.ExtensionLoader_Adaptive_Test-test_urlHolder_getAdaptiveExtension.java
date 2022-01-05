@Test public void test_urlHolder_getAdaptiveExtension() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  Map<String,String> map=new HashMap<String,String>();
  map.put("ext2","impl1");
  URL url=new URL("p1","1.2.3.4",1010,"path1",map);
  UrlHolder holder=new UrlHolder();
  holder.setUrl(url);
  String echo=ext.echo(holder,"haha");
  assertEquals("Ext2Impl1-echo",echo);
}
