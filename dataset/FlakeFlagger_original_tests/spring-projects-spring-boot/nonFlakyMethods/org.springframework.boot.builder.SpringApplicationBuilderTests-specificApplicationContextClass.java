@Test public void specificApplicationContextClass() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder().sources(ExampleConfig.class).contextClass(StaticApplicationContext.class);
  this.context=application.run();
  assertThat(this.context,is(instanceOf(StaticApplicationContext.class)));
}
