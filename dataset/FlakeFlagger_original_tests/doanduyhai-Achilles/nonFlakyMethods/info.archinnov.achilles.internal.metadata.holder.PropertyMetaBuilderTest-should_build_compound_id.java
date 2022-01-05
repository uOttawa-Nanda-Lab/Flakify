@Test public void should_build_compound_id() throws Exception {
  EmbeddedIdProperties props=new EmbeddedIdProperties(null,null,null,null,null,null,null,null);
  PropertyMeta built=PropertyMetaBuilder.factory().type(EMBEDDED_ID).propertyName("prop").accessors(accessors).objectMapper(objectMapper).consistencyLevels(Pair.create(ONE,ALL)).embeddedIdProperties(props).build(Void.class,EmbeddedKey.class);
  assertThat(built.type()).isEqualTo(EMBEDDED_ID);
  assertThat(built.getPropertyName()).isEqualTo("prop");
  assertThat(built.<EmbeddedKey>getValueClass()).isEqualTo(EmbeddedKey.class);
  assertThat(built.isEmbeddedId()).isTrue();
  assertThat(built.getReadConsistencyLevel()).isEqualTo(ONE);
  assertThat(built.getWriteConsistencyLevel()).isEqualTo(ALL);
  assertThat(built.getTranscoder()).isInstanceOf(CompoundTranscoder.class);
}
