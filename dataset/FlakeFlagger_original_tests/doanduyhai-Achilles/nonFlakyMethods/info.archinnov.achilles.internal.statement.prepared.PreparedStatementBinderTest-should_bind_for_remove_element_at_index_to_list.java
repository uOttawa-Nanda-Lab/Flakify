@Test(expected=IllegalStateException.class) public void should_bind_for_remove_element_at_index_to_list() throws Exception {
  when(changeSet.getChangeType()).thenReturn(REMOVE_FROM_LIST_AT_INDEX);
  binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
}
