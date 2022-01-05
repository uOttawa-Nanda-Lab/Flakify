@Test public void test_charArray_withType() throws Exception {
  char[] data=new char[]{'a','中','无'};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(char[])deserialize.readObject(char[].class));
  try {
    deserialize.readObject(char[].class);
    fail();
  }
 catch (  IOException expected) {
  }
}
