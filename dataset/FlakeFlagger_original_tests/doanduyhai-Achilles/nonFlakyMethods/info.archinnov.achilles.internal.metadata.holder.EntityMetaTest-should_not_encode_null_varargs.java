@Test public void should_not_encode_null_varargs() throws Exception {
  EntityMeta meta=new EntityMeta();
  final Object[] encoded=meta.encodeBoundValuesForTypedQueries(null);
  assertThat(encoded).hasSize(0);
}
