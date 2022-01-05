@Test public void testDefaultProtocol(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/override-protocol.xml");
  ctx.start();
  ProtocolConfig protocolConfig=ctx.getBean(ProtocolConfig.class);
  assertThat(protocolConfig.getName(),is("dubbo"));
}
