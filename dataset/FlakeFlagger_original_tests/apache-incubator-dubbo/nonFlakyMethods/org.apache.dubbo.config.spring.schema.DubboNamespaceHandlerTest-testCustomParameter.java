@Test public void testCustomParameter(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/customize-parameter.xml");
  ctx.start();
  ProtocolConfig protocolConfig=ctx.getBean(ProtocolConfig.class);
  assertThat(protocolConfig.getParameters().size(),is(1));
  assertThat(protocolConfig.getParameters().get("protocol-paramA"),is("protocol-paramA"));
  ServiceBean serviceBean=ctx.getBean(ServiceBean.class);
  assertThat(serviceBean.getParameters().size(),is(1));
  assertThat(serviceBean.getParameters().get("service-paramA"),is("service-paramA"));
}
