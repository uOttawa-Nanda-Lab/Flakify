@Test public void should_get_id_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.ID);
  when(row.getString("property")).thenReturn("value");
  when(pm.decode("value")).thenReturn("value");
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat(actual).isEqualTo("value");
}
