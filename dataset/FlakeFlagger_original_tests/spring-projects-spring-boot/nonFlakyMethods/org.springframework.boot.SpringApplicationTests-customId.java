@Test public void customId() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  this.context=application.run("--spring.application.name=foo");
  assertThat(this.context.getId(),startsWith("foo"));
}
