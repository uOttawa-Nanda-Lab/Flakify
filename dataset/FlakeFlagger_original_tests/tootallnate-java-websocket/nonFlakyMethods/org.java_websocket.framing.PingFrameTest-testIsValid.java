@Test public void testIsValid(){
  PingFrame frame=new PingFrame();
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setFin(false);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setFin(true);
  frame.setRSV1(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setRSV1(false);
  frame.setRSV2(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setRSV2(false);
  frame.setRSV3(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
}
