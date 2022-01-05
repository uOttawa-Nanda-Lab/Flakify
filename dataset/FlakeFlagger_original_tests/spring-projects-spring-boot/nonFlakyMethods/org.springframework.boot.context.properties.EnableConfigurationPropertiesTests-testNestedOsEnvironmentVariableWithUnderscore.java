@Test public void testNestedOsEnvironmentVariableWithUnderscore(){
  EnvironmentTestUtils.addEnvironment(this.context,"NAME:foo","NESTED_NAME:bar");
  this.context.register(NestedConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(NestedProperties.class).length);
  assertEquals("foo",this.context.getBean(NestedProperties.class).name);
  assertEquals("bar",this.context.getBean(NestedProperties.class).nested.name);
}
