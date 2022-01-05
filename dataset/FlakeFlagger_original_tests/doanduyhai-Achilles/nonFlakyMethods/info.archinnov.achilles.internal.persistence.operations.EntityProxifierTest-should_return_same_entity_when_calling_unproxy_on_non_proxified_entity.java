@Test public void should_return_same_entity_when_calling_unproxy_on_non_proxified_entity() throws Exception {
  CompleteBean realObject=new CompleteBean();
  CompleteBean actual=proxifier.removeProxy(realObject);
  assertThat(actual).isSameAs(realObject);
}
