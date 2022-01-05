@Test public void should_build_map() throws Exception {
  PropertyMeta built=PropertyMetaBuilder.factory().type(MAP).propertyName("prop").accessors(accessors).objectMapper(objectMapper).build(Integer.class,String.class);
  assertThat(built.type()).isEqualTo(MAP);
  assertThat(built.getPropertyName()).isEqualTo("prop");
  assertThat(built.<Integer>getKeyClass()).isEqualTo(Integer.class);
  assertThat(built.<String>getValueClass()).isEqualTo(String.class);
  assertThat(built.isEmbeddedId()).isFalse();
  assertThat(built.getTranscoder()).isInstanceOf(MapTranscoder.class);
}
