@Test public void should_test_is_embedded_id() throws Exception {
  assertThat(PropertyType.COUNTER.isEmbeddedId()).isFalse();
  assertThat(PropertyType.ID.isEmbeddedId()).isFalse();
  assertThat(PropertyType.SIMPLE.isEmbeddedId()).isFalse();
  assertThat(PropertyType.LIST.isEmbeddedId()).isFalse();
  assertThat(PropertyType.MAP.isEmbeddedId()).isFalse();
  assertThat(PropertyType.EMBEDDED_ID.isEmbeddedId()).isTrue();
}
