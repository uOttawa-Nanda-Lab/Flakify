@Test(timeout=3000) public void test_LoopReference() throws Exception {
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("k1","v1");
  map.put("self",map);
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(map);
  objectOutput.flushBuffer();
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
  ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
  @SuppressWarnings("unchecked") Map<String,Object> output=(Map<String,Object>)deserialize.readObject();
  assertEquals("v1",output.get("k1"));
  assertSame(output,output.get("self"));
}
