@Test public void test_StringArray_withType() throws Exception {
  String[] data=new String[]{"1","b"};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,deserialize.readObject(String[].class));
  try {
    deserialize.readObject(String[].class);
    fail();
  }
 catch (  ArrayIndexOutOfBoundsException e) {
  }
}
