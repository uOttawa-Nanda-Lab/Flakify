@Test public void should_generate_update_for_removed_elements() throws Exception {
  changeSet.setChanges.add("a");
  when(pm.encode(changeSet.setChanges)).thenReturn(changeSet.setChanges);
  Object[] vals=changeSet.generateUpdateForRemovedElements(update(),false).right;
  assertThat((Set<Object>)vals[0]).containsExactly("a");
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property-?;");
}
