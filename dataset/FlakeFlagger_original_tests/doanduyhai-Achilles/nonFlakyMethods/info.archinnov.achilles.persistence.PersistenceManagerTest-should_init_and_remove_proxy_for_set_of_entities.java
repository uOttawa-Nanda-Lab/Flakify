@Test public void should_init_and_remove_proxy_for_set_of_entities() throws Exception {
  Set<CompleteBean> entities=Sets.newHashSet(entity);
  when(facade.initialize(entities)).thenReturn(entities);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  when(proxifier.removeProxy(entities)).thenReturn(entities);
  Set<CompleteBean> actual=manager.initAndRemoveProxy(entities);
  assertThat(actual).isSameAs(entities);
}
