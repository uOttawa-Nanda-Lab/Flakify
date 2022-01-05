@Test public void testClassWithInnerValues() throws Exception {
  String json="{\"a1\":\"1\",\"a2\":\"2\",\"list\":[\"1\",\"2\"]}";
  ComplexData data=new ComplexData();
  data.a1=factory.getReference(ISomeActor.class,"1");
  data.a2=factory.getReference(ISomeActor.class,"2");
  data.list=Arrays.asList(data.a1,data.a2);
  ObjectMapper mapper=createMapper();
  assertEquals(json,mapper.writeValueAsString(data));
  assertEquals(data,mapper.readValue(json,ComplexData.class));
}
