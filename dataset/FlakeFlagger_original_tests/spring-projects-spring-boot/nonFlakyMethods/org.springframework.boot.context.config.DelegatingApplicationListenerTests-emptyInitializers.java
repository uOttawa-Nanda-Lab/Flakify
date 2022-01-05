@Test public void emptyInitializers() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"context.listener.classes:");
  this.listener.onApplicationEvent(new ApplicationEnvironmentPreparedEvent(new SpringApplication(),new String[0],this.context.getEnvironment()));
}
