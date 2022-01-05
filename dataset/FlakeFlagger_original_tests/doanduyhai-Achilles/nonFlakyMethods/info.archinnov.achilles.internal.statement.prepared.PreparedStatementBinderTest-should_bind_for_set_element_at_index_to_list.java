@Test(expected=IllegalStateException.class) public void should_bind_for_set_element_at_index_to_list() throws Exception {
  when(changeSet.getChangeType()).thenReturn(SET_TO_LIST_AT_INDEX);
  binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
}
