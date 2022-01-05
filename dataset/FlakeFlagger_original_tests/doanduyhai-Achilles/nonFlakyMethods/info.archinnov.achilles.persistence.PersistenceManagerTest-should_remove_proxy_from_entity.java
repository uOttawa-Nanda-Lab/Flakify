@Test public void should_remove_proxy_from_entity() throws Exception {
  when(proxifier.removeProxy(entity)).thenReturn(entity);
  CompleteBean actual=manager.removeProxy(entity);
  assertThat(actual).isSameAs(entity);
}
