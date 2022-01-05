@Test public void testIgnoreNestedPropertiesBinding(){
  removeSystemProperties();
  this.context.register(IgnoreNestedTestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo","nested.name:bar");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(IgnoreNestedTestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
