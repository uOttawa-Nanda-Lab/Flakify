@Test public void testNoExceptionOnValidation(){
  this.context.register(NoExceptionIfInvalidTestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(NoExceptionIfInvalidTestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestProperties.class).name);
}
