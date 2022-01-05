@Test public void test_Float() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeFloat(1.28F);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertTrue(1.28F == deserialize.readFloat());
  try {
    deserialize.readFloat();
    fail();
  }
 catch (  IOException expected) {
  }
}
