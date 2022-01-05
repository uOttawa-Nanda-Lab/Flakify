@Test public void should_get_null_list_change_at_index() throws Exception {
  ElementAtIndex actual=changeSet.getEncodedListChangeAtIndex();
  assertThat(actual).isNull();
  verifyZeroInteractions(pm);
}
