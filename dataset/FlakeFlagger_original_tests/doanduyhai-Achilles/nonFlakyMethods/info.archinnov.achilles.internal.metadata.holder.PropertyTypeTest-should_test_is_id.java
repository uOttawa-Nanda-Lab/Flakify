@Test public void should_test_is_id() throws Exception {
  assertThat(PropertyType.COUNTER.isId()).isFalse();
  assertThat(PropertyType.ID.isId()).isTrue();
  assertThat(PropertyType.SIMPLE.isId()).isFalse();
  assertThat(PropertyType.LIST.isId()).isFalse();
  assertThat(PropertyType.MAP.isId()).isFalse();
  assertThat(PropertyType.EMBEDDED_ID.isId()).isTrue();
}
