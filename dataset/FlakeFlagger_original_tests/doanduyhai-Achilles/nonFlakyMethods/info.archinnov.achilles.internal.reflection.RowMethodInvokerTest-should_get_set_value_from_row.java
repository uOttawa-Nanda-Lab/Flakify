@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_get_set_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SET);
  Set<String> set=Sets.newHashSet("value");
  when(row.getSet("property",String.class)).thenReturn(set);
  when(pm.decode(set)).thenReturn((Set)set);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat((Set)actual).containsAll(set);
}
