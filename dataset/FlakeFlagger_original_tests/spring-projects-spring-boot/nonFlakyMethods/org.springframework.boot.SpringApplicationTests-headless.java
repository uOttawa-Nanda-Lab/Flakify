@Test public void headless() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.run();
  assertThat(System.getProperty("java.awt.headless"),equalTo("true"));
}
