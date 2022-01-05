@Test public void headlessFalse() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.setHeadless(false);
  application.run();
  assertThat(System.getProperty("java.awt.headless"),equalTo("false"));
}
