@Test public void testList() throws Exception {
  String json="[\"1\",\"2\"]";
  ISomeActor actor1=factory.getReference(ISomeActor.class,"1");
  ISomeActor actor2=factory.getReference(ISomeActor.class,"2");
  ObjectMapper mapper=createMapper();
  List<ISomeActor> actors=Arrays.asList(actor1,actor2);
  String listJson=mapper.writeValueAsString(actors);
  assertEquals(json,listJson);
  assertEquals(actors,mapper.readValue(listJson,mapper.getTypeFactory().constructCollectionType(List.class,ISomeActor.class)));
}
