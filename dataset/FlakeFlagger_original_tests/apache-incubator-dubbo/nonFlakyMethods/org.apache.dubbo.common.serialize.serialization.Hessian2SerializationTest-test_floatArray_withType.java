@Test public void test_floatArray_withType() throws Exception {
  float[] data=new float[]{37F,-3.14F,123456.7F};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(float[])deserialize.readObject(),0.0001F);
  try {
    deserialize.readObject(float[].class);
    fail();
  }
 catch (  ArrayIndexOutOfBoundsException e) {
  }
}
