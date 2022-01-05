@Test public void test_getAdaptiveExtension_defaultAdaptiveKey() throws Exception {
{
    SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
    Map<String,String> map=new HashMap<String,String>();
    URL url=new URL("p1","1.2.3.4",1010,"path1",map);
    String echo=ext.echo(url,"haha");
    assertEquals("Ext1Impl1-echo",echo);
  }
{
    SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
    Map<String,String> map=new HashMap<String,String>();
    map.put("simple.ext","impl2");
    URL url=new URL("p1","1.2.3.4",1010,"path1",map);
    String echo=ext.echo(url,"haha");
    assertEquals("Ext1Impl2-echo",echo);
  }
}
