/** 
 * Test destory registry, exporter can be normal by destroyed
 */
@Test public void testDestoryRegistry(){
  URL newRegistryUrl=registryUrl.addParameter(Constants.EXPORT_KEY,serviceUrl);
  Invoker<RegistryProtocolTest> invoker=new MockInvoker<RegistryProtocolTest>(RegistryProtocolTest.class,newRegistryUrl);
  Exporter<?> exporter=protocol.export(invoker);
  destroyRegistryProtocol();
  assertEquals(false,exporter.getInvoker().isAvailable());
}
