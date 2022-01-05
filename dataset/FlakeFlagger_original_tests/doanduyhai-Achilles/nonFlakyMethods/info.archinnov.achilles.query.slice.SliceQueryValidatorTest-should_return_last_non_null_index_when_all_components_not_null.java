@Test public void should_return_last_non_null_index_when_all_components_not_null() throws Exception {
  assertThat(validator.getLastNonNullIndex(Arrays.<Object>asList(11L,"name",12.0))).isEqualTo(2);
}
