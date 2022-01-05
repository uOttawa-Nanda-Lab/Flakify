@Test public void should_return_last_non_null_index_when_hole_in_component() throws Exception {
  assertThat(validator.getLastNonNullIndex(Arrays.<Object>asList(11L,null,12))).isEqualTo(0);
}
