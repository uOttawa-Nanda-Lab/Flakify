@Test public void should_get_mapper_for_type() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  context.setObjectMapperFactory(factory);
  ObjectMapper mapper=new ObjectMapper();
  when(factory.getMapper(String.class)).thenReturn(mapper);
  final ObjectMapper actual=context.getMapperFor(String.class);
  assertThat(actual).isSameAs(mapper);
}
