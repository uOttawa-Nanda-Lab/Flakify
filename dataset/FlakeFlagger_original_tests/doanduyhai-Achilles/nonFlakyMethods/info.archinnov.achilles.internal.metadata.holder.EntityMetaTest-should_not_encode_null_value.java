@Test public void should_not_encode_null_value() throws Exception {
  EntityMeta meta=new EntityMeta();
  final Object[] encoded=meta.encodeBoundValuesForTypedQueries(new Object[]{null});
  assertThat(encoded).hasSize(1);
  assertThat(encoded[0]).isEqualTo(null);
}
