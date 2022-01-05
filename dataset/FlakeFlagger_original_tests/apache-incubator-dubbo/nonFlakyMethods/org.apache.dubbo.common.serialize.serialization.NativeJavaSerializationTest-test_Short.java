@Test public void test_Short() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeShort((short)123);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertEquals((short)123,deserialize.readShort());
  try {
    deserialize.readShort();
    fail();
  }
 catch (  IOException expected) {
  }
}
