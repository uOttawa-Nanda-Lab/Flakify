@Test public void should_generate_update_for_added_entries_with_bind_marker() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  when(pm.encode("a")).thenReturn("a");
  Object[] vals=changeSet.generateUpdateForAddedEntries(update(),true).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property+:property;");
}
