@Test public void testIsValid(){
  TextFrame frame=new TextFrame();
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
}
