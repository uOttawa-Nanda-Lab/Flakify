@Test public void addJmxAuto(){
  this.context.register(JmxAutoConfiguration.class,IntegrationAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(HeaderChannelRegistry.class));
  this.context.close();
}
