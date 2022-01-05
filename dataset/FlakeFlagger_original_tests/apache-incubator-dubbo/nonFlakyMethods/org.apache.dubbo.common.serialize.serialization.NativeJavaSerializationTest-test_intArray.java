@Test public void test_intArray() throws Exception {
  int[] data=new int[]{234,0,-1};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(int[])deserialize.readObject());
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
