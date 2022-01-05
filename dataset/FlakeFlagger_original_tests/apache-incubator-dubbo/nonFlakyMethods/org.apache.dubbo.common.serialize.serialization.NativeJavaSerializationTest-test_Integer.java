@Test public void test_Integer() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeInt(1);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  int i=deserialize.readInt();
  assertEquals(1,i);
  try {
    deserialize.readInt();
    fail();
  }
 catch (  IOException expected) {
  }
}
