@Test public void test_URL_mutable_withType() throws Exception {
  URL data=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan&noValue");
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  URL actual=(URL)deserialize.readObject(URL.class);
  assertEquals(data,actual);
  assertEquals(data.getParameters(),actual.getParameters());
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
