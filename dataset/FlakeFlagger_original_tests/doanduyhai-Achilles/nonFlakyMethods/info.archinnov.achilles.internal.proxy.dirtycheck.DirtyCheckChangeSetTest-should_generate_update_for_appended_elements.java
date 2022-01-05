@Test public void should_generate_update_for_appended_elements() throws Exception {
  changeSet.listChanges.add("a");
  when(pm.encode(changeSet.listChanges)).thenReturn(changeSet.listChanges);
  Object[] vals=changeSet.generateUpdateForAppendedElements(update(),false).right;
  assertThat((List<Object>)vals[0]).containsExactly("a");
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property=property+?;");
}
