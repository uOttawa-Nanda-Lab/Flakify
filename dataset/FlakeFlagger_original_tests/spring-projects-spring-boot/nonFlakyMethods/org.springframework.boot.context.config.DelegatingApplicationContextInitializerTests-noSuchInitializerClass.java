@Test public void noSuchInitializerClass() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"context.initializer.classes:missing.madeup.class");
  this.thrown.expect(ApplicationContextException.class);
  this.initializer.initialize(context);
}
