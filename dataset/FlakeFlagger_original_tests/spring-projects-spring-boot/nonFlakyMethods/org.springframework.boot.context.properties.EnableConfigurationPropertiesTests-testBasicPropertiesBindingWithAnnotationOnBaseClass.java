@Test public void testBasicPropertiesBindingWithAnnotationOnBaseClass(){
  this.context.register(DerivedConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(DerivedProperties.class).length);
  assertEquals("foo",this.context.getBean(BaseProperties.class).name);
}
