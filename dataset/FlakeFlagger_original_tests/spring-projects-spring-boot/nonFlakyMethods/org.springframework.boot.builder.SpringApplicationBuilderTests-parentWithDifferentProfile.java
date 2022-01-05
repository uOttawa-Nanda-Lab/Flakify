@Test public void parentWithDifferentProfile() throws Exception {
  SpringApplicationBuilder shared=new SpringApplicationBuilder(ExampleConfig.class).profiles("node").properties("transport=redis");
  SpringApplicationBuilder application=shared.child(ChildConfig.class).profiles("admin").web(false);
  shared.profiles("parent");
  this.context=application.run();
  assertThat(this.context.getEnvironment().acceptsProfiles("node","admin"),is(true));
  assertThat(this.context.getParent().getEnvironment().acceptsProfiles("node","parent"),is(true));
  assertThat(this.context.getParent().getEnvironment().acceptsProfiles("admin"),is(false));
}
