@Test public void testDefaultPort(){
  RegistryProtocol registryProtocol=new RegistryProtocol();
  assertEquals(9090,registryProtocol.getDefaultPort());
}
