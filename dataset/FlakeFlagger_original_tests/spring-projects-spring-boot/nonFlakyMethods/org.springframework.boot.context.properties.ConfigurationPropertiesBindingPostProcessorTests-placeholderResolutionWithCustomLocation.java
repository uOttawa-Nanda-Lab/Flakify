@Test public void placeholderResolutionWithCustomLocation() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"fooValue:bar");
  this.context.register(CustomConfigurationLocation.class);
  this.context.refresh();
  assertThat(this.context.getBean(CustomConfigurationLocation.class).getFoo(),equalTo("bar"));
}
