@Test public void should_normalize_canonical_classname() throws Exception {
  String canonicalName="org.achilles.entity.ClassName";
  String normalized=TableNameNormalizer.normalizerAndValidateColumnFamilyName(canonicalName);
  assertThat(normalized).isEqualTo("ClassName");
}
