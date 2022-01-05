@Test public void defaultApplicationContextForWeb() throws Exception {
  SpringApplication application=new SpringApplication(ExampleWebConfig.class);
  application.setWebEnvironment(true);
  this.context=application.run();
  assertThat(this.context,instanceOf(AnnotationConfigEmbeddedWebApplicationContext.class));
}
