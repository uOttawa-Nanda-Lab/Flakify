@Test public void should_build_simple_with_object_as_value() throws Exception {
  PropertyMeta built=PropertyMetaBuilder.factory().type(SIMPLE).propertyName("prop").accessors(accessors).objectMapper(objectMapper).build(Void.class,Bean.class);
  assertThat(built.type()).isEqualTo(SIMPLE);
  assertThat(built.getPropertyName()).isEqualTo("prop");
  assertThat(built.<Bean>getValueClass()).isEqualTo(Bean.class);
  assertThat(built.isEmbeddedId()).isFalse();
  assertThat(built.getTranscoder()).isInstanceOf(SimpleTranscoder.class);
}
