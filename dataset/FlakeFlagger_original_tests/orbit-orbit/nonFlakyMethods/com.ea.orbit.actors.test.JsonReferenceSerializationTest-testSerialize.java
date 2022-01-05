@Test public void testSerialize() throws Exception {
  String json="\"123\"";
  ISomeActor actor=factory.getReference(ISomeActor.class,"123");
  ObjectMapper mapper=createMapper();
  assertEquals(json,mapper.writeValueAsString(actor));
}
