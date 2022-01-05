@Test public void testArrayPropertiesBinding(){
  this.context.register(TestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo","array:1,2,3");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(TestProperties.class).length);
  assertEquals(3,this.context.getBean(TestProperties.class).getArray().length);
}
