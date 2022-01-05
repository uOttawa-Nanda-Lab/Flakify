@Test public void should_exception_when_invoking_list_getter_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.LIST);
  when(row.getList("property",String.class)).thenThrow(new RuntimeException(""));
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot retrieve list property 'property' from CQL Row");
  invoker.invokeOnRowForList(row,pm,"property",String.class);
}
