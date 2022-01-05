@Test public void should_return_last_non_null_index_when_some_components_are_null() throws Exception {
  assertThat(validator.getLastNonNullIndex(Arrays.<Object>asList(11L,null,null))).isEqualTo(0);
}
