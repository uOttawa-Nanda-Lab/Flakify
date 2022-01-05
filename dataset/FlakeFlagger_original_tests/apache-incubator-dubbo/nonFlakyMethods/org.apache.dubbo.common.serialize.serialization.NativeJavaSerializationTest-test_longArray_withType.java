@Test public void test_longArray_withType() throws Exception {
  long[] data=new long[]{234,0,-1};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(long[])deserialize.readObject(long[].class));
  try {
    deserialize.readObject(long[].class);
    fail();
  }
 catch (  IOException expected) {
  }
}
