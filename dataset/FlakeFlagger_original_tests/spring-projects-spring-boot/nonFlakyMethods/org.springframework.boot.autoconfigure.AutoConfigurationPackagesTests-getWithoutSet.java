@Test public void getWithoutSet() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(EmptyConfig.class);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Unable to retrieve @EnableAutoConfiguration base packages");
  AutoConfigurationPackages.get(context.getBeanFactory());
}
