@Test public void should_return_null_for_cql_ordering_component_if_no_multikey() throws Exception {
  PropertyMeta meta=new PropertyMeta();
  assertThat(meta.getOrderingComponent()).isNull();
}
