@Test public void propertyPlaceholdersOverride() throws Exception {
  this.context.register(PropertyPlaceholderAutoConfiguration.class,PlaceholderConfig.class,PlaceholdersOverride.class);
  EnvironmentTestUtils.addEnvironment(this.context,"foo:two");
  this.context.refresh();
  assertEquals("spam",this.context.getBean(PlaceholderConfig.class).getFoo());
}
