@Test public void test_Bool() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeBool(false);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertFalse(deserialize.readBool());
  try {
    deserialize.readBool();
    fail();
  }
 catch (  IOException expected) {
  }
}
