@Test public void testDeserialize() throws Exception {
  String json="\"123\"";
  ISomeActor actor=factory.getReference(ISomeActor.class,"123");
  ObjectMapper mapper=createMapper();
  assertEquals(actor,mapper.readValue(json,ISomeActor.class));
}
