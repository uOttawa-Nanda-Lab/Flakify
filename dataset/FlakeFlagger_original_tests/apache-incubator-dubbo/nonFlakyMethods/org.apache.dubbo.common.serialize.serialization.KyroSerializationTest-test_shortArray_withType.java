@Test public void test_shortArray_withType() throws Exception {
  short[] data=new short[]{37,39,12};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(short[])deserialize.readObject(short[].class));
  try {
    deserialize.readObject(short[].class);
    fail();
  }
 catch (  IOException expected) {
  }
}
