@Test public void parentFirstWithDifferentProfileAndExplicitEnvironment() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).environment(new StandardEnvironment()).profiles("node").properties("transport=redis").child(ChildConfig.class).profiles("admin").web(false);
  this.context=application.run();
  assertThat(this.context.getEnvironment().acceptsProfiles("node","admin"),is(true));
  assertThat(this.context.getParent().getEnvironment().acceptsProfiles("admin"),is(true));
}
