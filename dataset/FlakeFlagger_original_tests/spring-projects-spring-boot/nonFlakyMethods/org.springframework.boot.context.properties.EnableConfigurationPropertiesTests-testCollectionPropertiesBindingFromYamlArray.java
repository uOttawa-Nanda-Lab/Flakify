@Test public void testCollectionPropertiesBindingFromYamlArray(){
  this.context.register(TestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo","list[0]:1","list[1]:2");
  this.context.refresh();
  assertEquals(2,this.context.getBean(TestProperties.class).getList().size());
}
