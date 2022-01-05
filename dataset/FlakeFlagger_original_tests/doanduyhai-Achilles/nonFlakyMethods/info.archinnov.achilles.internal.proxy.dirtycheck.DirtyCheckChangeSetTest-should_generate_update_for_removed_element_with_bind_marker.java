@Test public void should_generate_update_for_removed_element_with_bind_marker() throws Exception {
  changeSet.setChanges.add("a");
  Object[] vals=changeSet.generateUpdateForRemovedElements(update(),true).right;
  assertThat(vals[0]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property-:property;");
}
