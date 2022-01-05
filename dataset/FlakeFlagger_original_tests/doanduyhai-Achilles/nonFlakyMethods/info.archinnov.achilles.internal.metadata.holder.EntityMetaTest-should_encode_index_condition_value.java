@Test public void should_encode_index_condition_value() throws Exception {
  final IndexCondition indexCondition=new IndexCondition("name",PropertyType.COUNTER);
  PropertyMeta nameMeta=mock(PropertyMeta.class);
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptCounters(asList(nameMeta));
  when(nameMeta.getCQL3PropertyName()).thenReturn("name");
  when(nameMeta.encode(PropertyType.COUNTER)).thenReturn("COUNTER");
  final Object encoded=meta.encodeIndexConditionValue(indexCondition);
  verify(nameMeta).encode(PropertyType.COUNTER);
  assertThat(encoded).isInstanceOf(String.class).isEqualTo("COUNTER");
  assertThat(indexCondition.getColumnValue()).isEqualTo("COUNTER");
}
