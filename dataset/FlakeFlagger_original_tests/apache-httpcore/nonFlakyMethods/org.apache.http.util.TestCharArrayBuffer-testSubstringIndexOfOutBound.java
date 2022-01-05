@Test public void testSubstringIndexOfOutBound(){
  CharArrayBuffer buffer=new CharArrayBuffer(16);
  buffer.append("stuff");
  try {
    buffer.substring(-2,10);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.substringTrimmed(-2,10);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.substring(12,10);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.substringTrimmed(12,10);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.substring(2,1);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.substringTrimmed(2,1);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
}
