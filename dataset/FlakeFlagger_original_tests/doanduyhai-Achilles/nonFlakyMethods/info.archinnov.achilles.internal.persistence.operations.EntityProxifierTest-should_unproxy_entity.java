@Test public void should_unproxy_entity() throws Exception {
  when(interceptor.getTarget()).thenReturn(realProxy);
  Factory actual=proxifier.removeProxy(realProxy);
  assertThat(actual).isSameAs(realProxy);
}
