@Test public void parentContext(){
  this.context.register(IntegrationAutoConfiguration.class);
  this.context.refresh();
  AnnotationConfigApplicationContext parent=this.context;
  this.context=new AnnotationConfigApplicationContext();
  this.context.setParent(parent);
  this.context.register(IntegrationAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(HeaderChannelRegistry.class));
  ((ConfigurableApplicationContext)this.context.getParent()).close();
  this.context.close();
}
