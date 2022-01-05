@Test public void test_getExtension_WithWrapper() throws Exception {
  WrappedExt impl1=ExtensionLoader.getExtensionLoader(WrappedExt.class).getExtension("impl1");
  assertThat(impl1,anyOf(instanceOf(Ext5Wrapper1.class),instanceOf(Ext5Wrapper2.class)));
  WrappedExt impl2=ExtensionLoader.getExtensionLoader(WrappedExt.class).getExtension("impl2");
  assertThat(impl2,anyOf(instanceOf(Ext5Wrapper1.class),instanceOf(Ext5Wrapper2.class)));
  URL url=new URL("p1","1.2.3.4",1010,"path1");
  int echoCount1=Ext5Wrapper1.echoCount.get();
  int echoCount2=Ext5Wrapper2.echoCount.get();
  assertEquals("Ext5Impl1-echo",impl1.echo(url,"ha"));
  assertEquals(echoCount1 + 1,Ext5Wrapper1.echoCount.get());
  assertEquals(echoCount2 + 1,Ext5Wrapper2.echoCount.get());
}
