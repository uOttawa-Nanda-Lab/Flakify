@Test public void should_init_and_remove_proxy_for_list_of_entities() throws Exception {
  List<CompleteBean> entities=Arrays.asList(entity);
  when(facade.initialize(entities)).thenReturn(entities);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  when(proxifier.removeProxy(entities)).thenReturn(entities);
  List<CompleteBean> actual=manager.initAndRemoveProxy(entities);
  assertThat(actual).isSameAs(entities);
}
