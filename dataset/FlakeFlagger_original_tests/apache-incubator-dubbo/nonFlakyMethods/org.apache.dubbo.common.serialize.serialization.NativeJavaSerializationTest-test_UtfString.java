@Test public void test_UtfString() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeUTF("123中华人民共和国");
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertEquals("123中华人民共和国",deserialize.readUTF());
  try {
    deserialize.readUTF();
    fail();
  }
 catch (  IOException expected) {
  }
}
