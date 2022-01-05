@Test public void should_unproxy_collection_of_entities() throws Exception {
  Collection<Factory> proxies=new ArrayList<Factory>();
  proxies.add(realProxy);
  when(interceptor.getTarget()).thenReturn(realProxy);
  Collection<Factory> actual=proxifier.removeProxy(proxies);
  assertThat(actual).containsExactly(realProxy);
}
