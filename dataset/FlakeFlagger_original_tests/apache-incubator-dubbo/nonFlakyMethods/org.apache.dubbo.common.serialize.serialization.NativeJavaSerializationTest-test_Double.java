@Test public void test_Double() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeDouble(1.28);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertTrue(1.28 == deserialize.readDouble());
  try {
    deserialize.readDouble();
    fail();
  }
 catch (  IOException expected) {
  }
}
