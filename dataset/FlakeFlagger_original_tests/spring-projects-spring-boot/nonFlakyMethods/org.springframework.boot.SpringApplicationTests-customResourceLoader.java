@Test public void customResourceLoader() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ResourceLoader resourceLoader=new DefaultResourceLoader();
  application.setResourceLoader(resourceLoader);
  this.context=application.run();
  verify(application.getLoader()).setResourceLoader(resourceLoader);
}
