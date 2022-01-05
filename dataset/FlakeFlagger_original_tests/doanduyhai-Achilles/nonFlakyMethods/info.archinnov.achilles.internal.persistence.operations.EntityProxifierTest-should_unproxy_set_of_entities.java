@Test public void should_unproxy_set_of_entities() throws Exception {
  Set<Factory> proxies=new HashSet<Factory>();
  proxies.add(realProxy);
  when(interceptor.getTarget()).thenReturn(realProxy);
  Collection<Factory> actual=proxifier.removeProxy(proxies);
  assertThat(actual).containsExactly(realProxy);
}
