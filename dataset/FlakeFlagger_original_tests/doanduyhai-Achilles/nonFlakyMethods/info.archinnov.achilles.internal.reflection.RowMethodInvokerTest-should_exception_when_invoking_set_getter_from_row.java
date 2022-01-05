@Test public void should_exception_when_invoking_set_getter_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SET);
  when(row.getSet("property",String.class)).thenThrow(new RuntimeException(""));
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot retrieve set property 'property' from CQL Row");
  invoker.invokeOnRowForSet(row,pm,"property",String.class);
}
