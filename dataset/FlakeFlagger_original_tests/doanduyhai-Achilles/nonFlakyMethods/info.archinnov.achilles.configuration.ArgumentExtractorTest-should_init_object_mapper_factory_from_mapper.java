@Test public void should_init_object_mapper_factory_from_mapper() throws Exception {
  configMap.put(OBJECT_MAPPER,mapper);
  ObjectMapperFactory actual=extractor.initObjectMapperFactory(configMap);
  assertThat(actual).isNotNull();
  assertThat(actual.getMapper(Long.class)).isSameAs(mapper);
}
