@Test public void should_unproxy_entryset_containing_proxy() throws Exception {
  Map<Integer,Factory> map=new HashMap<Integer,Factory>();
  map.put(1,realProxy);
  Map.Entry<Integer,Factory> entry=map.entrySet().iterator().next();
  when(interceptor.getTarget()).thenReturn(realProxy);
  Map.Entry<Integer,Factory> actual=proxifier.removeProxy(entry);
  assertThat(actual).isSameAs(entry);
  assertThat(actual.getValue()).isSameAs(realProxy);
}
