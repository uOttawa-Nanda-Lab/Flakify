@Test public void should_return_raw_entity_for_raw_typed_query_with_bound_values() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").buid();
  manager.persist(entity);
  String queryString="SELECT name FROM CompleteBean LIMIT ?";
  List<CompleteBean> actual=manager.rawTypedQuery(CompleteBean.class,queryString,3).get();
  assertThat(actual).hasSize(1);
  assertThat(actual.get(0).getName()).isEqualTo(entity.getName());
}
