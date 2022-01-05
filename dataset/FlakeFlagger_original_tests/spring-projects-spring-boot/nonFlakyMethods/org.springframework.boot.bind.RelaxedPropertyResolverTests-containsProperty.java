@Test public void containsProperty() throws Exception {
  assertThat(this.resolver.containsProperty("my-string"),equalTo(true));
  assertThat(this.resolver.containsProperty("myString"),equalTo(true));
  assertThat(this.resolver.containsProperty("my_string"),equalTo(true));
  assertThat(this.resolver.containsProperty("my-missing"),equalTo(false));
}
