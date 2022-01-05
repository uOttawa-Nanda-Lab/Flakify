@Test public void should_get_encoded_set_changes() throws Exception {
  changeSet.setChanges.add("a");
  when(pm.encode(changeSet.setChanges)).thenReturn(changeSet.setChanges);
  Set<Object> actual=changeSet.getEncodedSetChanges();
  assertThat(actual).containsExactly("a");
}
