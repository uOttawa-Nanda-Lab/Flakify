@Test public void should_generate_update_for_assign_list_value_with_bind_marker() throws Exception {
  changeSet.listChanges.add("a");
  Object[] vals=changeSet.generateUpdateForAssignValueToList(update(),true).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=:property;");
}
