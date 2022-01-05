@Test public void testIsValid(){
  ContinuousFrame frame=new ContinuousFrame();
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
}
