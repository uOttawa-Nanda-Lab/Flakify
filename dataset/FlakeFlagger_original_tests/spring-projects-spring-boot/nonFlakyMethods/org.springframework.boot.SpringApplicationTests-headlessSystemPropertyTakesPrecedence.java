@Test public void headlessSystemPropertyTakesPrecedence() throws Exception {
  System.setProperty("java.awt.headless","false");
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.run();
  assertThat(System.getProperty("java.awt.headless"),equalTo("false"));
}
