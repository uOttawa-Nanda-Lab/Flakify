@Test public void should_init_object_mapper_factory() throws Exception {
  configMap.put(OBJECT_MAPPER_FACTORY,factory);
  ObjectMapperFactory actual=extractor.initObjectMapperFactory(configMap);
  assertThat(actual).isSameAs(factory);
}
