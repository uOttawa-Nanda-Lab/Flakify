@Test public void should_generate_update_for_remove_all() throws Exception {
  Object[] vals=changeSet.generateUpdateForRemoveAll(update(),false).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=null;");
}
