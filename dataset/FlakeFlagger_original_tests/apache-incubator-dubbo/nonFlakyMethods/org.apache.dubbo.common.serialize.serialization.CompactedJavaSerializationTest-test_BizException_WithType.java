@Test public void test_BizException_WithType() throws Exception {
  BizException e=new BizException("Hello");
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(e);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  Object read=deserialize.readObject(BizException.class);
  assertEquals("Hello",((BizException)read).getMessage());
}
