@Test public void testValidationWithJSR303(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfigurationWithJSR303.class);
  try {
    this.context.refresh();
    fail("Expected exception");
  }
 catch (  BeanCreationException ex) {
    BindException bex=(BindException)ex.getRootCause();
    assertEquals(2,bex.getErrorCount());
  }
}
