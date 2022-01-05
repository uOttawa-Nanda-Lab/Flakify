@Test public void should_not_load_simple_entity_when_not_found() throws Exception {
  when(meta.isClusteredCounter()).thenReturn(false);
  when(meta.instanciate()).thenReturn(entity);
  CompleteBean actual=loader.load(context,CompleteBean.class);
  assertThat(actual).isNull();
  verifyZeroInteractions(mapper,counterLoader);
}
