@Test public void should_get_raw_list_changes() throws Exception {
  List<Object> actual=changeSet.getEncodedListChanges();
  assertThat(actual).isEmpty();
  verifyZeroInteractions(pm);
}
