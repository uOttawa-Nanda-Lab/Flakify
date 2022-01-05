@Test public void propertiesAsProperties() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder().sources(ExampleConfig.class).contextClass(StaticApplicationContext.class).properties(StringUtils.splitArrayElementsIntoProperties(new String[]{"bar=foo"},"="));
  this.context=application.run();
  assertThat(this.context.getEnvironment().getProperty("bar"),is(equalTo("foo")));
}
