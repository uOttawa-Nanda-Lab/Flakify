@Test public void testBytesToString() throws Exception {
  String s=constructString(SWISS_GERMAN_HELLO);
  byte[] utf=s.getBytes("UTF-8");
  byte[] latin1=s.getBytes("ISO-8859-1");
  String s1=EncodingUtils.getString(utf,"UTF-8");
  String s2=EncodingUtils.getString(latin1,"ISO-8859-1");
  Assert.assertEquals(s,s1);
  Assert.assertEquals(s,s2);
  try {
    EncodingUtils.getString(null,0,0,"UTF-8");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getString(null,"UTF-8");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getString(new byte[]{},null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getString(new byte[]{},"");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
