@Test public void testProviderXml(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/demo-provider.xml");
  ctx.start();
  ProtocolConfig protocolConfig=ctx.getBean(ProtocolConfig.class);
  assertThat(protocolConfig,not(nullValue()));
  assertThat(protocolConfig.getName(),is("dubbo"));
  assertThat(protocolConfig.getPort(),is(20813));
  ApplicationConfig applicationConfig=ctx.getBean(ApplicationConfig.class);
  assertThat(applicationConfig,not(nullValue()));
  assertThat(applicationConfig.getName(),is("demo-provider"));
  DemoService service=ctx.getBean(DemoService.class);
  assertThat(service,not(nullValue()));
}
