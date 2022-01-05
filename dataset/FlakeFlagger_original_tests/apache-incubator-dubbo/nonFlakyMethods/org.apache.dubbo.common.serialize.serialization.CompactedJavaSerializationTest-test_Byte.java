@Test public void test_Byte() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeByte((byte)123);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertEquals((byte)123,deserialize.readByte());
  try {
    deserialize.readByte();
    fail();
  }
 catch (  IOException expected) {
  }
}
