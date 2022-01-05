@Test public void should_return_true_when_type_supported() throws Exception {
  assertThat(PropertyParser.isSupportedNativeType(Long.class)).isTrue();
}
