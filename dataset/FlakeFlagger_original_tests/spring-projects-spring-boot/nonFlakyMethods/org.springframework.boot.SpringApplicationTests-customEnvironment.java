@Test public void customEnvironment() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run();
  verify(application.getLoader()).setEnvironment(environment);
}
