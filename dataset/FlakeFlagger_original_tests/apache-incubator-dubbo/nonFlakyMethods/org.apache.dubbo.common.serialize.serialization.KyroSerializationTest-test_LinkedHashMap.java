@Test public void test_LinkedHashMap() throws Exception {
  LinkedHashMap<String,String> data=new LinkedHashMap<String,String>();
  data.put("1","a");
  data.put("2","b");
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(data);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  Object read=deserialize.readObject();
  assertTrue(read instanceof LinkedHashMap);
  @SuppressWarnings("unchecked") String key1=((LinkedHashMap<String,String>)read).entrySet().iterator().next().getKey();
  assertEquals("1",key1);
  assertEquals(data,read);
  try {
    deserialize.readObject();
    fail();
  }
 catch (  IOException expected) {
  }
}
