@Test public void reactorIsAvailable(){
  this.context.register(ReactorAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(Reactor.class));
  this.context.close();
}
