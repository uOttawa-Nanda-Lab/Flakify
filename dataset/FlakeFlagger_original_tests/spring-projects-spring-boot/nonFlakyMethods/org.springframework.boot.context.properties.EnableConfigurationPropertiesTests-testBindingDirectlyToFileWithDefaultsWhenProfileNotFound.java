@Test public void testBindingDirectlyToFileWithDefaultsWhenProfileNotFound(){
  this.context.register(ResourceBindingProperties.class,TestConfiguration.class);
  this.context.getEnvironment().addActiveProfile("nonexistent");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(ResourceBindingProperties.class).length);
  assertEquals("foo",this.context.getBean(ResourceBindingProperties.class).name);
}
