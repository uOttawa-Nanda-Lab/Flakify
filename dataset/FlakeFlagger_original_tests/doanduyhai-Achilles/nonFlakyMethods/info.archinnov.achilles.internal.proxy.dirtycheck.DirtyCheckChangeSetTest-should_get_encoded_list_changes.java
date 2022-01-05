@Test public void should_get_encoded_list_changes() throws Exception {
  changeSet.listChanges.add("a");
  when(pm.encode(changeSet.listChanges)).thenReturn(changeSet.listChanges);
  List<Object> actual=changeSet.getEncodedListChanges();
  assertThat(actual).containsExactly("a");
}
