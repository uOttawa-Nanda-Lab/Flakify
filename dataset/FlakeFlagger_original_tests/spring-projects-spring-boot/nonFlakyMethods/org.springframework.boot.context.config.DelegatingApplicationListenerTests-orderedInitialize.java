@Test public void orderedInitialize() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"context.listener.classes:" + MockInitB.class.getName() + ","+ MockInitA.class.getName());
  this.listener.onApplicationEvent(new ApplicationEnvironmentPreparedEvent(new SpringApplication(),new String[0],this.context.getEnvironment()));
  this.context.getBeanFactory().registerSingleton("testListener",this.listener);
  this.context.refresh();
  assertThat(this.context.getBeanFactory().getSingleton("a"),equalTo((Object)"a"));
  assertThat(this.context.getBeanFactory().getSingleton("b"),equalTo((Object)"b"));
}
