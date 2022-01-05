@SuppressWarnings("rawtypes") @Test public void should_return_long_as_compatible_java_type() throws Exception {
  assertThat((Class)toCompatibleJavaType(Long.class)).isEqualTo(Long.class);
}
