@Test public void testValidationWithoutJSR303(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfigurationWithoutJSR303.class);
  try {
    this.context.refresh();
    fail("Expected exception");
  }
 catch (  BeanCreationException ex) {
    BindException bex=(BindException)ex.getRootCause();
    assertEquals(1,bex.getErrorCount());
  }
}
