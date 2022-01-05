@Test public void specificApplicationContextClass() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setApplicationContextClass(StaticApplicationContext.class);
  this.context=application.run();
  assertThat(this.context,instanceOf(StaticApplicationContext.class));
}
