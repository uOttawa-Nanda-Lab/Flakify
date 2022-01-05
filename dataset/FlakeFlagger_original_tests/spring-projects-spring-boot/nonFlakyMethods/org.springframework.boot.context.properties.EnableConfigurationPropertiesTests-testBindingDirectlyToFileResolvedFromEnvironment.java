@Test public void testBindingDirectlyToFileResolvedFromEnvironment(){
  EnvironmentTestUtils.addEnvironment(this.context,"binding.location:classpath:other.yml");
  this.context.register(ResourceBindingProperties.class,TestConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(ResourceBindingProperties.class).length);
  assertEquals("other",this.context.getBean(ResourceBindingProperties.class).name);
}
