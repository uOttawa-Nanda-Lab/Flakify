@Test public void getPropertyNoSeparator() throws Exception {
  assertThat(this.resolver.getProperty("myobject"),equalTo("object"));
  assertThat(this.resolver.getProperty("my-object"),equalTo("object"));
}
