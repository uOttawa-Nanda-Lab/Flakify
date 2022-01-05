@Test public void testSubsystem() throws Exception {
  KernelServicesBuilder builder=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  if (!mainServices.isSuccessfulBoot()) {
    Assert.fail(mainServices.getBootError().toString());
  }
  ServerConfig serverConfig=getMSCService(mainServices.getContainer(),WSServices.CONFIG_SERVICE,ServerConfig.class);
  Assert.assertTrue(serverConfig.isModifySOAPAddress());
  Assert.assertEquals("localhost",serverConfig.getWebServiceHost());
  Assert.assertEquals(9895,serverConfig.getWebServicePort());
  Assert.assertEquals(9944,serverConfig.getWebServiceSecurePort());
  Assert.assertEquals("https",serverConfig.getWebServiceUriScheme());
  serverConfig.reloadClientConfigs();
  ClientConfig clCfg=serverConfig.getClientConfig("My-Client-Config");
  Assert.assertNotNull(clCfg);
  Assert.assertEquals(1,clCfg.getProperties().size());
  Assert.assertEquals("bar3",clCfg.getProperties().get("foo3"));
  Assert.assertEquals(2,clCfg.getPreHandlerChains().size());
  Map<String,UnifiedHandlerChainMetaData> map=new HashMap<String,UnifiedHandlerChainMetaData>();
  for (  UnifiedHandlerChainMetaData uhc : clCfg.getPreHandlerChains()) {
    map.put(uhc.getId(),uhc);
  }
  Assert.assertTrue(map.get("my-handlers").getHandlers().isEmpty());
  Assert.assertEquals("##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM",map.get("my-handlers").getProtocolBindings());
  Assert.assertEquals(1,map.get("my-handlers2").getHandlers().size());
  Assert.assertEquals("MyHandler",map.get("my-handlers2").getHandlers().get(0).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.MyHandler",map.get("my-handlers2").getHandlers().get(0).getHandlerClass());
  Assert.assertEquals("##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM",map.get("my-handlers").getProtocolBindings());
  Assert.assertEquals(1,clCfg.getPostHandlerChains().size());
  Assert.assertEquals("my-handlers2",clCfg.getPostHandlerChains().get(0).getId());
  Assert.assertEquals(1,clCfg.getPostHandlerChains().get(0).getHandlers().size());
  Assert.assertEquals("MyHandler2",clCfg.getPostHandlerChains().get(0).getHandlers().get(0).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.MyHandler2",clCfg.getPostHandlerChains().get(0).getHandlers().get(0).getHandlerClass());
  Assert.assertEquals("##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM",clCfg.getPostHandlerChains().get(0).getProtocolBindings());
  serverConfig.reloadEndpointConfigs();
  EndpointConfig epCfg=serverConfig.getEndpointConfig("Standard-Endpoint-Config");
  Assert.assertNotNull(epCfg);
  Assert.assertTrue(epCfg.getProperties().isEmpty());
  Assert.assertTrue(epCfg.getPreHandlerChains().isEmpty());
  Assert.assertTrue(epCfg.getPostHandlerChains().isEmpty());
  epCfg=serverConfig.getEndpointConfig("Recording-Endpoint-Config");
  Assert.assertNotNull(epCfg);
  Assert.assertEquals(2,epCfg.getProperties().size());
  Assert.assertEquals("bar",epCfg.getProperties().get("foo"));
  Assert.assertEquals("bar2",epCfg.getProperties().get("foo2"));
  Assert.assertEquals(1,epCfg.getPreHandlerChains().size());
  Assert.assertEquals("recording-handlers",epCfg.getPreHandlerChains().get(0).getId());
  Assert.assertEquals(2,epCfg.getPreHandlerChains().get(0).getHandlers().size());
  Assert.assertEquals("RecordingHandler",epCfg.getPreHandlerChains().get(0).getHandlers().get(0).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.RecordingServerHandler",epCfg.getPreHandlerChains().get(0).getHandlers().get(0).getHandlerClass());
  Assert.assertEquals("AnotherRecordingHandler",epCfg.getPreHandlerChains().get(0).getHandlers().get(1).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.RecordingServerHandler",epCfg.getPreHandlerChains().get(0).getHandlers().get(1).getHandlerClass());
  Assert.assertEquals("##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM",epCfg.getPreHandlerChains().get(0).getProtocolBindings());
  Assert.assertEquals(1,epCfg.getPostHandlerChains().size());
  Assert.assertEquals("recording-handlers2",epCfg.getPostHandlerChains().get(0).getId());
  Assert.assertEquals(2,epCfg.getPostHandlerChains().get(0).getHandlers().size());
  Assert.assertEquals("RecordingHandler2",epCfg.getPostHandlerChains().get(0).getHandlers().get(0).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.RecordingServerHandler",epCfg.getPostHandlerChains().get(0).getHandlers().get(0).getHandlerClass());
  Assert.assertEquals("AnotherRecordingHandler2",epCfg.getPostHandlerChains().get(0).getHandlers().get(1).getHandlerName());
  Assert.assertEquals("org.jboss.ws.common.invocation.RecordingServerHandler",epCfg.getPostHandlerChains().get(0).getHandlers().get(1).getHandlerClass());
  Assert.assertEquals("##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM",epCfg.getPostHandlerChains().get(0).getProtocolBindings());
}
