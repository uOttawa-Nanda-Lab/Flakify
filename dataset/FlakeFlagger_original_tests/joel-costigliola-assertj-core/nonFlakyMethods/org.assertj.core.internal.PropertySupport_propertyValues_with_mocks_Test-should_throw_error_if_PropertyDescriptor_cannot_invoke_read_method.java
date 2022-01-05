@Test public void should_throw_error_if_PropertyDescriptor_cannot_invoke_read_method() throws Exception {
  RuntimeException thrownOnPurpose=new RuntimeException("Thrown on purpose");
  PropertyDescriptor real=getProperty("age",yoda);
  when(descriptor.invokeReadMethod(real,yoda)).thenThrow(thrownOnPurpose);
  try {
    propertySupport.propertyValues("age",Long.class,employees);
    fail("expecting an IntrospectionError to be thrown");
  }
 catch (  IntrospectionError expected) {
    assertSame(thrownOnPurpose,expected.getCause());
    String msg=String.format("Unable to obtain the value of the property <'age'> from <%s>",yoda.toString());
    assertEquals(msg,expected.getMessage());
  }
}
