@Test public void should_do_nothing_when_null_row() throws Exception {
  PropertyMeta pm=mock(PropertyMeta.class);
  entityMapper.setPropertyToEntity(null,pm,entity);
  verifyZeroInteractions(cqlRowInvoker);
}
