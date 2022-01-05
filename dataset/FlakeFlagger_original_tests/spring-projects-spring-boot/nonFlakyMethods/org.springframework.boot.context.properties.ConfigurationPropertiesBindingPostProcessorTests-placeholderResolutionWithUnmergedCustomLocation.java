@Test public void placeholderResolutionWithUnmergedCustomLocation() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"fooValue:bar");
  this.context.register(UnmergedCustomConfigurationLocation.class);
  this.context.refresh();
  assertThat(this.context.getBean(UnmergedCustomConfigurationLocation.class).getFoo(),equalTo("${fooValue}"));
}
