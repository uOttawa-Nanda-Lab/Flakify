@Test public void getVersionFound() throws Exception {
  assertThat(this.resolver.getVersion("a1"),equalTo("0"));
}
