@Test(expected=IndexOutOfBoundsException.class) public void testIllegalConstructorBadOff2() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  new ByteArrayEntity(bytes,bytes.length + 1,bytes.length);
}
