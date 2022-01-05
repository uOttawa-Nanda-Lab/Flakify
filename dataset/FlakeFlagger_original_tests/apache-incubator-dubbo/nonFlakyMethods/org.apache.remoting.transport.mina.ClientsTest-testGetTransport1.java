@Test public void testGetTransport1(){
  String name="mina";
  assertEquals(MinaTransporter.class,ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(name).getClass());
}
