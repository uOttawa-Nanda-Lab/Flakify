@Test public void should_remove_proxy_for_set_of_entities() throws Exception {
  Set<CompleteBean> proxies=new HashSet<>();
  when(proxifier.removeProxy(proxies)).thenReturn(proxies);
  Set<CompleteBean> actual=manager.removeProxy(proxies);
  assertThat(actual).isSameAs(proxies);
}
