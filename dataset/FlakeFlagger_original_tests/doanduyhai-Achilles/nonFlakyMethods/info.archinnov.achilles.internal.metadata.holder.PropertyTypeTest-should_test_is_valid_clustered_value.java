@Test public void should_test_is_valid_clustered_value() throws Exception {
  assertThat(PropertyType.COUNTER.isValidClusteredValueType()).isTrue();
  assertThat(PropertyType.ID.isValidClusteredValueType()).isFalse();
  assertThat(PropertyType.SIMPLE.isValidClusteredValueType()).isTrue();
  assertThat(PropertyType.LIST.isValidClusteredValueType()).isFalse();
  assertThat(PropertyType.MAP.isValidClusteredValueType()).isFalse();
  assertThat(PropertyType.EMBEDDED_ID.isValidClusteredValueType()).isFalse();
}
