@Test(expected=IndexOutOfBoundsException.class) public void testIllegalConstructorBadOff1() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  new ByteArrayEntity(bytes,-1,bytes.length);
}
