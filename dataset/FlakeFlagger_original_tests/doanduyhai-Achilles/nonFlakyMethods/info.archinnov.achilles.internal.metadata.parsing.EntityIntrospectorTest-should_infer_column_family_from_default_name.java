@Test public void should_infer_column_family_from_default_name() throws Exception {
  String cfName=introspector.inferTableName(CompleteBean.class,"canonicalName");
  assertThat(cfName).isEqualTo("canonicalName");
}
