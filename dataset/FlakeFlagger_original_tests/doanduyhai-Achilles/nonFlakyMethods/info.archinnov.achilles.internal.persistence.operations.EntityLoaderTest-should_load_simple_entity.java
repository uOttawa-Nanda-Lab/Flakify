@Test public void should_load_simple_entity() throws Exception {
  Row row=mock(Row.class);
  when(meta.isClusteredCounter()).thenReturn(false);
  when(context.loadEntity()).thenReturn(row);
  when(meta.instanciate()).thenReturn(entity);
  CompleteBean actual=loader.load(context,CompleteBean.class);
  assertThat(actual).isSameAs(entity);
  verify(mapper).setNonCounterPropertiesToEntity(row,meta,entity);
  verifyZeroInteractions(counterLoader);
}
