@Test public void loadSources() throws Exception {
  Object[] sources={ExampleConfig.class,"a",TestCommandLineRunner.class};
  TestSpringApplication application=new TestSpringApplication(sources);
  application.setWebEnvironment(false);
  application.setUseMockLoader(true);
  application.run();
  Set<Object> initialSources=application.getSources();
  assertThat(initialSources.toArray(),equalTo(sources));
}
