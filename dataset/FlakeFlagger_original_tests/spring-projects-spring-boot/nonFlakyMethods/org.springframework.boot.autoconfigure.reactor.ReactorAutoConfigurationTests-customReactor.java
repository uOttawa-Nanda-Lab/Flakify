@Test public void customReactor(){
  this.context.register(TestConfiguration.class,ReactorAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(Reactor.class));
  this.context.close();
}
