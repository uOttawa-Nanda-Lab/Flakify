@Test public void testBasicPropertiesBinding(){
  this.context.register(TestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(TestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
