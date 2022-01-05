@Test public void integrationIsAvailable(){
  this.context.register(IntegrationAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(HeaderChannelRegistry.class));
  this.context.close();
}
