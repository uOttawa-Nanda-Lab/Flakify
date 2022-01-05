@Test public void should_deserialize_from_json() throws Exception {
  pmf.configContext=configContext;
  ObjectMapper mapper=new ObjectMapper();
  mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  when(configContext.getMapperFor(CompleteBean.class)).thenReturn(mapper);
  final CompleteBean actual=pmf.deserializeJson(CompleteBean.class,"{\"id\":10,\"name\":\"name\"}");
  assertThat(actual.getId()).isEqualTo(10L);
  assertThat(actual.getName()).isEqualTo("name");
  assertThat(actual.getFriends()).isNull();
  assertThat(actual.getFollowers()).isNull();
  assertThat(actual.getPreferences()).isNull();
}
