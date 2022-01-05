@Test public void test_longArray() throws Exception {
  long[] data=new long[]{234,0,-1};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(long[])deserialize.readObject());
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
