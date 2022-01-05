@Test public void profileAndProperties() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder().sources(ExampleConfig.class).contextClass(StaticApplicationContext.class).profiles("foo").properties("foo=bar");
  this.context=application.run();
  assertThat(this.context,is(instanceOf(StaticApplicationContext.class)));
  assertThat(this.context.getEnvironment().getProperty("foo"),is(equalTo("bucket")));
  assertThat(this.context.getEnvironment().acceptsProfiles("foo"),is(true));
}
