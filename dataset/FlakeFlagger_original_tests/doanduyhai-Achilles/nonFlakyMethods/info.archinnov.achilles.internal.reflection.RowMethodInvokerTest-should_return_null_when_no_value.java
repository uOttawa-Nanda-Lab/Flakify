@Test public void should_return_null_when_no_value() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SIMPLE);
  when(row.isNull("property")).thenReturn(true);
  assertThat(invoker.invokeOnRowForFields(row,pm)).isNull();
}
