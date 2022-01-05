@Test public void parentFirstCreationWithProfileAndDefaultArgs() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).profiles("node").properties("transport=redis").child(ChildConfig.class).web(false);
  this.context=application.run();
  assertThat(this.context.getEnvironment().acceptsProfiles("node"),is(true));
  assertThat(this.context.getEnvironment().getProperty("transport"),is(equalTo("redis")));
  assertThat(this.context.getParent().getEnvironment().acceptsProfiles("node"),is(true));
  assertThat(this.context.getParent().getEnvironment().getProperty("transport"),is(equalTo("redis")));
  assertThat(this.context.getEnvironment().getProperty("bar"),is(equalTo("spam")));
}
