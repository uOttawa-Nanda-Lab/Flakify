@Test public void propertyPlaceholderse() throws Exception {
  this.context.register(PropertyPlaceholderAutoConfiguration.class,PlaceholderConfig.class);
  EnvironmentTestUtils.addEnvironment(this.context,"foo:two");
  this.context.refresh();
  assertEquals("two",this.context.getBean(PlaceholderConfig.class).getFoo());
}
