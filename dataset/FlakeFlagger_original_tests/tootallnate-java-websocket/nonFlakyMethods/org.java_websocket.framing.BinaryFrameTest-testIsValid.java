@Test public void testIsValid(){
  BinaryFrame frame=new BinaryFrame();
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
}
