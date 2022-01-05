@Test public void should_encode_bound_values_for_native_type() throws Exception {
  EntityMeta meta=new EntityMeta();
  final Object[] encoded=meta.encodeBoundValuesForTypedQueries(new Object[]{"test"});
  assertThat(encoded).hasSize(1);
  assertThat(encoded[0]).isEqualTo("test");
}
