@Test public void getPropertyWithDefault() throws Exception {
  assertThat(this.resolver.getProperty("my-string","a"),equalTo("value"));
  assertThat(this.resolver.getProperty("my-missing","a"),equalTo("a"));
}
