@Test public void emptyInitializers() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"context.initializer.classes:");
  this.initializer.initialize(context);
}
