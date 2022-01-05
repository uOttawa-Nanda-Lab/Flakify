@Test public void should_generate_update_for_added_entries() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  when(pm.encode("a")).thenReturn("a");
  Object[] vals=changeSet.generateUpdateForAddedEntries(update(),false).right;
  assertThat((Map<Object,Object>)vals[0]).contains(entry(1,"a"));
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property+?;");
}
