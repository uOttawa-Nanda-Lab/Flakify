@Test public void should_remove_proxy_for_list_of_entities() throws Exception {
  List<CompleteBean> proxies=new ArrayList<>();
  when(proxifier.removeProxy(proxies)).thenReturn(proxies);
  List<CompleteBean> actual=manager.removeProxy(proxies);
  assertThat(actual).isSameAs(proxies);
}
