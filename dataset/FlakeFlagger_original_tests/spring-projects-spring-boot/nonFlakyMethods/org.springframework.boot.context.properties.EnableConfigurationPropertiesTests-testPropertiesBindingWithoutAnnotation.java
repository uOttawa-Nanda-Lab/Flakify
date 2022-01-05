@Test public void testPropertiesBindingWithoutAnnotation(){
  this.context.register(MoreConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(MoreProperties.class).length);
  assertEquals("foo",this.context.getBean(MoreProperties.class).name);
}
