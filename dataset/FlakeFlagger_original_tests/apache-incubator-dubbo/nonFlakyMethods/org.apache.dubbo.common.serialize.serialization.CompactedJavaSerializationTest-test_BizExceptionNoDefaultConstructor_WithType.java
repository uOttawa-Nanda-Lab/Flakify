@Test public void test_BizExceptionNoDefaultConstructor_WithType() throws Exception {
  BizExceptionNoDefaultConstructor e=new BizExceptionNoDefaultConstructor("Hello");
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(e);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  Object read=deserialize.readObject(BizExceptionNoDefaultConstructor.class);
  assertEquals("Hello",((BizExceptionNoDefaultConstructor)read).getMessage());
}
