@Test public void should_serialize_to_json() throws Exception {
  manager.configContext=configContext;
  ObjectMapper mapper=new ObjectMapper();
  mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  when(configContext.getMapperFor(CompleteBean.class)).thenReturn(mapper);
  CompleteBean entity=CompleteBeanTestBuilder.builder().id(10L).name("name").buid();
  final String serialized=manager.jsonSerialize(entity);
  assertThat(serialized).isEqualTo("{\"id\":10,\"name\":\"name\",\"friends\":[],\"followers\":[],\"preferences\":{}}");
}
