@Test public void testNestedPropertiesBinding(){
  this.context.register(NestedConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo","nested.name:bar");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(NestedProperties.class).length);
  assertEquals("foo",this.context.getBean(NestedProperties.class).name);
  assertEquals("bar",this.context.getBean(NestedProperties.class).nested.name);
}
