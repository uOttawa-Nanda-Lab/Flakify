@Test public void should_generate_update_for_removed_key() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  Object[] vals=changeSet.generateUpdateForRemovedKey(update(),false).right;
  assertThat(vals[0]).isEqualTo(1);
  assertThat(vals[1]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property[1]=null;");
}
