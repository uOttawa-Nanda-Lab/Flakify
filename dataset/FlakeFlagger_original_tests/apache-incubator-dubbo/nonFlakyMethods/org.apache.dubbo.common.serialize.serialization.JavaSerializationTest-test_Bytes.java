@Test public void test_Bytes() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeBytes("123中华人民共和国".getBytes());
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals("123中华人民共和国".getBytes(),deserialize.readBytes());
  try {
    deserialize.readBytes();
    fail();
  }
 catch (  IOException expected) {
  }
}
