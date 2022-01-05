@Test public void orderedInitialize() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"context.initializer.classes:" + MockInitB.class.getName() + ","+ MockInitA.class.getName());
  this.initializer.initialize(context);
  assertThat(context.getBeanFactory().getSingleton("a"),equalTo((Object)"a"));
  assertThat(context.getBeanFactory().getSingleton("b"),equalTo((Object)"b"));
}
