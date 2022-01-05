@Test public void should_build_simple() throws Exception {
  PropertyMeta built=PropertyMetaBuilder.factory().type(SIMPLE).propertyName("prop").accessors(accessors).field(field).objectMapper(objectMapper).consistencyLevels(Pair.create(ONE,ALL)).build(Void.class,String.class);
  assertThat(built.type()).isEqualTo(SIMPLE);
  assertThat(built.getPropertyName()).isEqualTo("prop");
  assertThat(built.<String>getValueClass()).isEqualTo(String.class);
  assertThat(built.getField()).isEqualTo(field);
  assertThat(built.isEmbeddedId()).isFalse();
  assertThat(built.getReadConsistencyLevel()).isEqualTo(ONE);
  assertThat(built.getWriteConsistencyLevel()).isEqualTo(ALL);
  assertThat(built.getTranscoder()).isInstanceOf(SimpleTranscoder.class);
}
