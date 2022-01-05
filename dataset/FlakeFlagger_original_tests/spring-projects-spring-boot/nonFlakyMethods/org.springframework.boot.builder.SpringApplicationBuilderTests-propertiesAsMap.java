@Test public void propertiesAsMap() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder().sources(ExampleConfig.class).contextClass(StaticApplicationContext.class).properties(Collections.<String,Object>singletonMap("bar","foo"));
  this.context=application.run();
  assertThat(this.context.getEnvironment().getProperty("bar"),is(equalTo("foo")));
}
