@Test public void test_boolArray_withType() throws Exception {
  boolean[] data=new boolean[]{true,false,true};
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  assertTrue(Arrays.equals(data,(boolean[])deserialize.readObject(boolean[].class)));
  try {
    deserialize.readObject(boolean[].class);
    fail();
  }
 catch (  IOException expected) {
  }
}
