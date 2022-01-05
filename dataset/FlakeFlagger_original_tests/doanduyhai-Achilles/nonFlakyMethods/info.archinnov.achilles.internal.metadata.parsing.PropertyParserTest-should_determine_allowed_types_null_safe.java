@Test public void should_determine_allowed_types_null_safe() throws Exception {
  assertThat(isSupportedType(null)).isFalse();
  assertThat(isSupportedType(ConsistencyLevel.class)).isTrue();
  assertThat(isSupportedType(String.class)).isTrue();
}
