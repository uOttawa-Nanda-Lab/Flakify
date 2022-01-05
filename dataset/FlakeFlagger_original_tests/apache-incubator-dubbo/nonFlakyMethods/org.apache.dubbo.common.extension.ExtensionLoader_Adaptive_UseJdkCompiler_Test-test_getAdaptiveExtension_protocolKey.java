@Test public void test_getAdaptiveExtension_protocolKey() throws Exception {
  UseProtocolKeyExt ext=ExtensionLoader.getExtensionLoader(UseProtocolKeyExt.class).getAdaptiveExtension();
{
    String echo=ext.echo(URL.valueOf("1.2.3.4:20880"),"s");
    assertEquals("Ext3Impl1-echo",echo);
    Map<String,String> map=new HashMap<String,String>();
    URL url=new URL("impl3","1.2.3.4",1010,"path1",map);
    echo=ext.echo(url,"s");
    assertEquals("Ext3Impl3-echo",echo);
    url=url.addParameter("key1","impl2");
    echo=ext.echo(url,"s");
    assertEquals("Ext3Impl2-echo",echo);
  }
{
    Map<String,String> map=new HashMap<String,String>();
    URL url=new URL(null,"1.2.3.4",1010,"path1",map);
    String yell=ext.yell(url,"s");
    assertEquals("Ext3Impl1-yell",yell);
    url=url.addParameter("key2","impl2");
    yell=ext.yell(url,"s");
    assertEquals("Ext3Impl2-yell",yell);
    url=url.setProtocol("impl3");
    yell=ext.yell(url,"d");
    assertEquals("Ext3Impl3-yell",yell);
  }
}
