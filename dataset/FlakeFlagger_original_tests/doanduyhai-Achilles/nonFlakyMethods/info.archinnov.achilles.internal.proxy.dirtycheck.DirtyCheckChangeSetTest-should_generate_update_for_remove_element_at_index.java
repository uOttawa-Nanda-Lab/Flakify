@Test public void should_generate_update_for_remove_element_at_index() throws Exception {
  changeSet.listChangeAtIndex=new ElementAtIndex(1,"a");
  when(pm.encode("a")).thenReturn("a");
  Object[] vals=changeSet.generateUpdateForRemovedAtIndexElement(update()).right;
  assertThat(vals[0]).isEqualTo(1);
  assertThat(vals[1]).isNull();
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property[1]=null;");
}
