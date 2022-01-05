@Test public void testGetTransport3(){
  String name="netty3";
  assertEquals(NettyTransporter.class,ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(name).getClass());
}
