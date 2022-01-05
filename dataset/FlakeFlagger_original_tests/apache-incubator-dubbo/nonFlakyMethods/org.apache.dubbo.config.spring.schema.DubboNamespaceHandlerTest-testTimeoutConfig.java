@Test public void testTimeoutConfig(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/provider-nested-service.xml");
  ctx.start();
  Map<String,ProviderConfig> providerConfigMap=ctx.getBeansOfType(ProviderConfig.class);
  assertThat(providerConfigMap.get("org.apache.dubbo.config.ProviderConfig").getTimeout(),is(2000));
}
