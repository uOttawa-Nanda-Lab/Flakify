@Test public void testOsEnvironmentVariableEmbeddedBinding(){
  EnvironmentTestUtils.addEnvironment(this.context,"SPRING_FOO_NAME:foo");
  this.context.register(EmbeddedTestConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(EmbeddedTestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
