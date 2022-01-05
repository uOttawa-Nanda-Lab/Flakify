@Test public void testPropertiesEmbeddedBinding(){
  this.context.register(EmbeddedTestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring_foo_name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(EmbeddedTestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
