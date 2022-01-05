@Test public void should_set_null_to_entity_when_no_value_from_row() throws Exception {
  PropertyMeta pm=mock(PropertyMeta.class);
  when(pm.isEmbeddedId()).thenReturn(false);
  when(pm.getPropertyName()).thenReturn("name");
  when(row.isNull("name")).thenReturn(true);
  entityMapper.setNonCounterPropertiesToEntity(row,entityMeta,entity);
  verify(pm,never()).setValueToField(eq(entity),any());
  verifyZeroInteractions(cqlRowInvoker);
}
