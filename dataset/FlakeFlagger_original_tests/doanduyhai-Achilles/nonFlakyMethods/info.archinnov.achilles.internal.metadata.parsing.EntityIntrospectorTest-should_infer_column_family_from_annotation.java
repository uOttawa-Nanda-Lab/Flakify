@Test public void should_infer_column_family_from_annotation() throws Exception {
  String cfName=introspector.inferTableName(BeanWithColumnFamilyName.class,"canonicalName");
  assertThat(cfName).isEqualTo("myOwnCF");
}
