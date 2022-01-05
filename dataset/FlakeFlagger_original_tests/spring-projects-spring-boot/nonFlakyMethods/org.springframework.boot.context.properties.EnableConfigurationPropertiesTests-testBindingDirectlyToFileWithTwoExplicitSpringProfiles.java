@Test public void testBindingDirectlyToFileWithTwoExplicitSpringProfiles(){
  this.context.register(ResourceBindingProperties.class,TestConfiguration.class);
  this.context.getEnvironment().setActiveProfiles("super","other");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(ResourceBindingProperties.class).length);
  assertEquals("spam",this.context.getBean(ResourceBindingProperties.class).name);
}
