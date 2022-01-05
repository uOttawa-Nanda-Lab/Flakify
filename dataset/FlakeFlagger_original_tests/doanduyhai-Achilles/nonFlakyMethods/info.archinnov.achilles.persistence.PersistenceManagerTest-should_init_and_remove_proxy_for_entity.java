@Test public void should_init_and_remove_proxy_for_entity() throws Exception {
  when(facade.initialize(entity)).thenReturn(entity);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  when(proxifier.removeProxy(entity)).thenReturn(entity);
  CompleteBean actual=manager.initAndRemoveProxy(entity);
  assertThat(actual).isSameAs(entity);
}
