@Test public void defaultCommandLineArgs() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setDefaultProperties(StringUtils.splitArrayElementsIntoProperties(new String[]{"baz=","bar=spam"},"="));
  application.setWebEnvironment(false);
  this.context=application.run("--bar=foo","bucket","crap");
  assertThat(this.context,instanceOf(AnnotationConfigApplicationContext.class));
  assertThat(getEnvironment().getProperty("bar"),equalTo("foo"));
  assertThat(getEnvironment().getProperty("baz"),equalTo(""));
}
