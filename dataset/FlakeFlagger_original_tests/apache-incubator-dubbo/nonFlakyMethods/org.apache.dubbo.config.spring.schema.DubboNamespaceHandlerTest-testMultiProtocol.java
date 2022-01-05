@Test public void testMultiProtocol(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/multi-protocol.xml");
  ctx.start();
  Map<String,ProtocolConfig> protocolConfigMap=ctx.getBeansOfType(ProtocolConfig.class);
  assertThat(protocolConfigMap.size(),is(2));
  ProtocolConfig rmiProtocolConfig=protocolConfigMap.get("rmi");
  assertThat(rmiProtocolConfig.getPort(),is(10991));
  ProtocolConfig dubboProtocolConfig=protocolConfigMap.get("dubbo");
  assertThat(dubboProtocolConfig.getPort(),is(20881));
}
