@Test public void testStringToBytesToString() throws Exception {
  String s=constructString(SWISS_GERMAN_HELLO);
  byte[] utf=s.getBytes("UTF-8");
  byte[] latin1=s.getBytes("ISO-8859-1");
  byte[] data1=EncodingUtils.getBytes(s,"UTF-8");
  byte[] data2=EncodingUtils.getBytes(s,"ISO-8859-1");
  Assert.assertNotNull(data1);
  Assert.assertEquals(utf.length,data1.length);
  for (int i=0; i < utf.length; i++) {
    Assert.assertEquals(utf[i],data1[i]);
  }
  Assert.assertNotNull(data2);
  Assert.assertEquals(latin1.length,data2.length);
  for (int i=0; i < latin1.length; i++) {
    Assert.assertEquals(latin1[i],data2[i]);
  }
  try {
    EncodingUtils.getBytes(null,"UTF-8");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getBytes("what not",null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getBytes("what not","");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
