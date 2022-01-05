@Test public void should_return_rows_for_native_query_with_bound_values() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").buid();
  manager.persist(entity);
  String nativeQuery="SELECT name FROM CompleteBean WHERE id = ?";
  List<TypedMap> actual=manager.nativeQuery(nativeQuery,entity.getId()).get();
  assertThat(actual).hasSize(1);
  TypedMap row=actual.get(0);
  assertThat(row.get("name")).isEqualTo("DuyHai");
}
