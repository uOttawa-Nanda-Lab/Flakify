@Test public void should_set_non_counter_properties_to_entity() throws Exception {
  PropertyMeta pm=mock(PropertyMeta.class);
  when(pm.isEmbeddedId()).thenReturn(false);
  when(pm.getPropertyName()).thenReturn("name");
  when(entityMeta.getAllMetasExceptCounters()).thenReturn(asList(pm));
  when(row.isNull("name")).thenReturn(false);
  when(cqlRowInvoker.invokeOnRowForFields(row,pm)).thenReturn("value");
  entityMapper.setNonCounterPropertiesToEntity(row,entityMeta,entity);
  verify(pm).setValueToField(entity,"value");
}
