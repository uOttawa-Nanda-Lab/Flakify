@Test public void should_encode_CAS_condition_value() throws Exception {
  final CASCondition CASCondition=new CASCondition("name",PropertyType.COUNTER);
  PropertyMeta nameMeta=mock(PropertyMeta.class);
  EntityMeta meta=new EntityMeta();
  meta.setAllMetasExceptCounters(asList(nameMeta));
  when(nameMeta.getCQL3PropertyName()).thenReturn("name");
  when(nameMeta.encode(PropertyType.COUNTER)).thenReturn("COUNTER");
  final Object encoded=meta.encodeCasConditionValue(CASCondition);
  verify(nameMeta).encode(PropertyType.COUNTER);
  assertThat(encoded).isInstanceOf(String.class).isEqualTo("COUNTER");
  assertThat(CASCondition.getValue()).isEqualTo("COUNTER");
}
