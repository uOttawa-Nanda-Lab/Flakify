@Test public void should_build_null_proxy() throws Exception {
  assertThat(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(null,context)).isNull();
}
