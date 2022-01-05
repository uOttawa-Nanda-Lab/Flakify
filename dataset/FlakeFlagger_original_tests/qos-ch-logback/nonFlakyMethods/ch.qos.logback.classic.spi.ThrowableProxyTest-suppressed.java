@Test public void suppressed() throws InvocationTargetException, IllegalAccessException {
  assumeNotNull(ADD_SUPPRESSED_METHOD);
  Exception ex=null;
  try {
    someMethod();
  }
 catch (  Exception e) {
    Exception fooException=new Exception("Foo");
    Exception barException=new Exception("Bar");
    addSuppressed(e,fooException);
    addSuppressed(e,barException);
    ex=e;
  }
  verify(ex);
}
