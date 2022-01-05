@Test public void should_exception_when_invoking_getter_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SIMPLE);
  when(row.getString("property")).thenThrow(new RuntimeException(""));
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot retrieve property 'property' for entity class 'null' from CQL Row");
  invoker.invokeOnRowForFields(row,pm);
}
