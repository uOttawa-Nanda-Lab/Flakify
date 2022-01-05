@Test public void test_Long() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeLong(123L);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertEquals(123L,deserialize.readLong());
  try {
    deserialize.readLong();
    fail();
  }
 catch (  IOException expected) {
  }
}
