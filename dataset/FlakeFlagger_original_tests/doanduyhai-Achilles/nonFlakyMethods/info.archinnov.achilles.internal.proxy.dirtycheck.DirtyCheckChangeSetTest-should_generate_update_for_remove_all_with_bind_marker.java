@Test public void should_generate_update_for_remove_all_with_bind_marker() throws Exception {
  Object[] vals=changeSet.generateUpdateForRemoveAll(update(),true).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=:property;");
}
