@Test public void genericNotSuitable() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"context.initializer.classes:" + NotSuitableInit.class.getName());
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("generic parameter");
  this.initializer.initialize(context);
}
