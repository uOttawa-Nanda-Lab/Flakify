@Test public void test_BytesRange() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeBytes("123中华人民共和国-新疆维吾尔自治区".getBytes(),1,9);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  byte[] expectedArray=new byte[9];
  System.arraycopy("123中华人民共和国-新疆维吾尔自治区".getBytes(),1,expectedArray,0,expectedArray.length);
  assertArrayEquals(expectedArray,deserialize.readBytes());
  try {
    deserialize.readBytes();
    fail();
  }
 catch (  IOException expected) {
  }
}
