@Test public void test_Byte_Multi() throws Exception {
  byte[] array=new byte[100];
  random.nextBytes(array);
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  for (  byte b : array) {
    objectOutput.writeByte(b);
  }
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  for (  byte b : array) {
    assertEquals(b,deserialize.readByte());
  }
  try {
    deserialize.readByte();
    fail();
  }
 catch (  IOException expected) {
  }
}
