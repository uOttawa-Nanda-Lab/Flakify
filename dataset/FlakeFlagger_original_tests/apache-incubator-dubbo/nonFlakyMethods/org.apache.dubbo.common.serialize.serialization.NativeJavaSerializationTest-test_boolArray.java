@Test public void test_boolArray() throws Exception {
  boolean[] data=new boolean[]{true,false,true};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertTrue(Arrays.equals(data,(boolean[])deserialize.readObject()));
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
