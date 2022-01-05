@Test public void testValidationWithSetter(){
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"test.foo:spam");
  this.context.register(TestConfigurationWithValidatingSetter.class);
  try {
    this.context.refresh();
    fail("Expected exception");
  }
 catch (  BeanCreationException ex) {
    BindException bex=(BindException)ex.getRootCause();
    assertEquals(1,bex.getErrorCount());
  }
}
