@Test public void should_get_encoded_list_change_at_index() throws Exception {
  changeSet.listChangeAtIndex=new ElementAtIndex(0,"a");
  when(pm.encode("a")).thenReturn("a");
  ElementAtIndex actual=changeSet.getEncodedListChangeAtIndex();
  assertThat(actual).isEqualTo(changeSet.listChangeAtIndex);
}
