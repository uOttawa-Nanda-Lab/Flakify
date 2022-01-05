@Test public void should_test_is_counter() throws Exception {
  assertThat(PropertyType.ID.isCounter()).isFalse();
  assertThat(PropertyType.SIMPLE.isCounter()).isFalse();
  assertThat(PropertyType.LIST.isCounter()).isFalse();
  assertThat(PropertyType.MAP.isCounter()).isFalse();
  assertThat(PropertyType.COUNTER.isCounter()).isTrue();
  assertThat(PropertyType.EMBEDDED_ID.isCounter()).isFalse();
}
