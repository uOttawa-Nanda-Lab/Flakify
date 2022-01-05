@Test public void testAsciiStringToBytes() throws Exception {
  String s="ascii only, I mean it!";
  byte[] ascii=s.getBytes("US-ASCII");
  byte[] data=EncodingUtils.getAsciiBytes(s);
  Assert.assertNotNull(data);
  Assert.assertEquals(ascii.length,data.length);
  for (int i=0; i < ascii.length; i++) {
    Assert.assertEquals(ascii[i],data[i]);
  }
  try {
    EncodingUtils.getAsciiBytes(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
