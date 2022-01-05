@Test public void should_return_null_when_unproxying_null() throws Exception {
  assertThat(proxifier.removeProxy((Object)null)).isNull();
}
