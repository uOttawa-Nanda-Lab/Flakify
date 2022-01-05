@Test public void test_getAdaptiveExtension_customizeAdaptiveKey() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
  Map<String,String> map=new HashMap<String,String>();
  map.put("key2","impl2");
  URL url=new URL("p1","1.2.3.4",1010,"path1",map);
  String echo=ext.yell(url,"haha");
  assertEquals("Ext1Impl2-yell",echo);
  url=url.addParameter("key1","impl3");
  echo=ext.yell(url,"haha");
  assertEquals("Ext1Impl3-yell",echo);
}
