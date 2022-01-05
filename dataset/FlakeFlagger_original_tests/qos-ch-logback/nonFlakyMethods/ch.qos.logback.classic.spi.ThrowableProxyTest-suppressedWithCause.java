@Test public void suppressedWithCause() throws InvocationTargetException, IllegalAccessException {
  assumeNotNull(ADD_SUPPRESSED_METHOD);
  Exception ex=null;
  try {
    someMethod();
  }
 catch (  Exception e) {
    ex=new Exception("Wrapper",e);
    Exception fooException=new Exception("Foo");
    Exception barException=new Exception("Bar");
    addSuppressed(ex,fooException);
    addSuppressed(e,barException);
  }
  verify(ex);
}
