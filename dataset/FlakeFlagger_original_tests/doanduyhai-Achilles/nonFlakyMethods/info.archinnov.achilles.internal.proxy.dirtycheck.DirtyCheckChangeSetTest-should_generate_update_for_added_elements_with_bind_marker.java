@Test public void should_generate_update_for_added_elements_with_bind_marker() throws Exception {
  changeSet.setChanges.add("a");
  Object[] vals=changeSet.generateUpdateForAddedElements(update(),true).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property+:property;");
}
