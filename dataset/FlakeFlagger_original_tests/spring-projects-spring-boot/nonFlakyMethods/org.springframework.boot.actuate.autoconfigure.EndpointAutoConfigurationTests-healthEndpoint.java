@Test public void healthEndpoint(){
  load(EmbeddedDataSourceConfiguration.class,EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  HealthEndpoint bean=this.context.getBean(HealthEndpoint.class);
  assertNotNull(bean);
  Health result=bean.invoke();
  assertNotNull(result);
  assertTrue("Wrong result: " + result,result.getDetails().containsKey("db"));
}
