@Test public void should_load_properties_into_object() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SIMPLE);
  Row row=mock(Row.class);
  when(context.loadProperty(pm)).thenReturn(row);
  loader.loadPropertyIntoObject(context,entity,pm);
  verify(mapper).setPropertyToEntity(row,pm,entity);
  verifyZeroInteractions(counterLoader);
}
