@Test public void testGetTransport3(){
  String name="netty4";
  assertEquals(NettyTransporter.class,ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(name).getClass());
}
