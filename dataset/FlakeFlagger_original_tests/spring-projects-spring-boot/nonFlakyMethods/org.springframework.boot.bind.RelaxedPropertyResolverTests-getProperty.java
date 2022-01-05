@Test public void getProperty() throws Exception {
  assertThat(this.resolver.getProperty("my-string"),equalTo("value"));
  assertThat(this.resolver.getProperty("my-missing"),nullValue());
}
