@Test public void defaultApplicationContext() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  this.context=application.run();
  assertThat(this.context,instanceOf(AnnotationConfigApplicationContext.class));
}
