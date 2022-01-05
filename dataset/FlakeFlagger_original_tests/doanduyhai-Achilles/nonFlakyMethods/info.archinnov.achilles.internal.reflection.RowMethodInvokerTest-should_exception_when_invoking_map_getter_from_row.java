@Test public void should_exception_when_invoking_map_getter_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.MAP);
  when(row.getMap("property",Integer.class,String.class)).thenThrow(new RuntimeException(""));
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot retrieve map property 'property' from CQL Row");
  invoker.invokeOnRowForMap(row,pm,"property",Integer.class,String.class);
}
