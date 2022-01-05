@Test public void testStrictPropertiesBinding(){
  removeSystemProperties();
  this.context.register(StrictTestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(StrictTestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
