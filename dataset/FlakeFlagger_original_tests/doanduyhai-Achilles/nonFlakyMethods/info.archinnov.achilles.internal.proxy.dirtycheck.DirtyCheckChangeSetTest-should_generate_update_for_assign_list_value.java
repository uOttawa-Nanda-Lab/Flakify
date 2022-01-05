@Test public void should_generate_update_for_assign_list_value() throws Exception {
  changeSet.listChanges.add("a");
  when(pm.encode(changeSet.listChanges)).thenReturn(changeSet.listChanges);
  Object[] vals=changeSet.generateUpdateForAssignValueToList(update(),false).right;
  assertThat((List<Object>)vals[0]).containsExactly("a");
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=?;");
}
