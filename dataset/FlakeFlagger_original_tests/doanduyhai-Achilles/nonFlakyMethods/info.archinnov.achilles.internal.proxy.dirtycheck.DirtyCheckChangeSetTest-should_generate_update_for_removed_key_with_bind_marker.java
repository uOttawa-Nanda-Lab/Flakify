@Test public void should_generate_update_for_removed_key_with_bind_marker() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  Object[] vals=changeSet.generateUpdateForRemovedKey(update(),true).right;
  assertThat(vals[0]).isEqualTo(1);
  assertThat(vals[1]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property[:key]=:nullValue;");
}
