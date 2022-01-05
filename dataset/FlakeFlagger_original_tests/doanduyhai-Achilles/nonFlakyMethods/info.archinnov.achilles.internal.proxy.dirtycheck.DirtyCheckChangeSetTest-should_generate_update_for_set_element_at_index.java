@Test public void should_generate_update_for_set_element_at_index() throws Exception {
  changeSet.listChangeAtIndex=new ElementAtIndex(1,"a");
  when(pm.encode("a")).thenReturn("a");
  Object[] vals=changeSet.generateUpdateForSetAtIndexElement(update()).right;
  assertThat(vals[0]).isEqualTo(1);
  assertThat(vals[1]).isEqualTo("a");
  assertThat(conditions.getQueryString()).isEqualTo("UPDATE table SET property[1]=?;");
}
