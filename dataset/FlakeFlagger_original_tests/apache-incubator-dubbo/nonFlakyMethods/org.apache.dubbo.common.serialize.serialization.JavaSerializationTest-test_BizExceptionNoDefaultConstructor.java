@Test public void test_BizExceptionNoDefaultConstructor() throws Exception {
  BizExceptionNoDefaultConstructor e=new BizExceptionNoDefaultConstructor("Hello");
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(e);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  Object read=deserialize.readObject();
  assertEquals("Hello",((BizExceptionNoDefaultConstructor)read).getMessage());
}
