@Test public void test_doubleArray() throws Exception {
  double[] data=new double[]{37D,-3.14D,123456.7D};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertArrayEquals(data,(double[])deserialize.readObject(),0.0001);
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
