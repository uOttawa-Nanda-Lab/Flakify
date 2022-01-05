@Test public void should_init_default_object_factory_mapper() throws Exception {
  ObjectMapperFactory actual=extractor.initObjectMapperFactory(configMap);
  assertThat(actual).isNotNull();
  ObjectMapper mapper=actual.getMapper(Integer.class);
  assertThat(mapper).isNotNull();
  assertThat(mapper.getSerializationConfig().getSerializationInclusion()).isEqualTo(JsonInclude.Include.NON_NULL);
  assertThat(mapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)).isFalse();
  Collection<AnnotationIntrospector> ais=mapper.getSerializationConfig().getAnnotationIntrospector().allIntrospectors();
  assertThat(ais).hasSize(2);
  Iterator<AnnotationIntrospector> iterator=ais.iterator();
  assertThat(iterator.next()).isInstanceOfAny(JacksonAnnotationIntrospector.class,JaxbAnnotationIntrospector.class);
  assertThat(iterator.next()).isInstanceOfAny(JacksonAnnotationIntrospector.class,JaxbAnnotationIntrospector.class);
}
