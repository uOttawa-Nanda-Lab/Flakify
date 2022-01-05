@SuppressWarnings("rawtypes") @Test public void should_return_String_as_compatible_java_type() throws Exception {
  assertThat((Class)toCompatibleJavaType(PropertyType.class)).isEqualTo(String.class);
}
