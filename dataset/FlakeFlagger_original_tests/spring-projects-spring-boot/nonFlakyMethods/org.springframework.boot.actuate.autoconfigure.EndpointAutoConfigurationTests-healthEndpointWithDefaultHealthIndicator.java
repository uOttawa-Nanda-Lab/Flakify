@Test public void healthEndpointWithDefaultHealthIndicator(){
  load(EndpointAutoConfiguration.class,HealthIndicatorAutoConfiguration.class);
  HealthEndpoint bean=this.context.getBean(HealthEndpoint.class);
  assertNotNull(bean);
  Health result=bean.invoke();
  assertNotNull(result);
}
