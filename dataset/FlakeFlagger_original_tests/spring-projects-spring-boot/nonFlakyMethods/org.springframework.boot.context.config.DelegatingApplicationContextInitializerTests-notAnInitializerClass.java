@Test public void notAnInitializerClass() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"context.initializer.classes:" + Object.class.getName());
  this.thrown.expect(IllegalArgumentException.class);
  this.initializer.initialize(context);
}
