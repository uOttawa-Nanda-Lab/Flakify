@Test public void should_generate_update_for_assign_map_value() throws Exception {
  changeSet.mapChanges.put(1,"a");
  when(pm.encodeKey(1)).thenReturn(1);
  when(pm.encode("a")).thenReturn("a");
  Object[] vals=changeSet.generateUpdateForAssignValueToMap(update(),false).right;
  assertThat((Map<Object,Object>)vals[0]).contains(entry(1,"a"));
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=?;");
}
