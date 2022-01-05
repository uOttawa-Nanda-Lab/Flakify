@Test(expected=IndexOutOfBoundsException.class) public void testIllegalConstructorBadLen() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  new ByteArrayEntity(bytes,0,bytes.length + 1);
}
