@Test public void should_encode_bound_values_for_enum_type() throws Exception {
  EntityMeta meta=new EntityMeta();
  final Object[] encoded=meta.encodeBoundValuesForTypedQueries(new Object[]{PropertyType.COUNTER});
  assertThat(encoded).hasSize(1);
  assertThat(encoded[0]).isEqualTo("COUNTER");
}
