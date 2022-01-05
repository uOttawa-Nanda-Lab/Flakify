@Test public void testBindingDirectlyToFileWithExplicitSpringProfile(){
  this.context.register(ResourceBindingProperties.class,TestConfiguration.class);
  this.context.getEnvironment().addActiveProfile("super");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(ResourceBindingProperties.class).length);
  assertEquals("bar",this.context.getBean(ResourceBindingProperties.class).name);
}
