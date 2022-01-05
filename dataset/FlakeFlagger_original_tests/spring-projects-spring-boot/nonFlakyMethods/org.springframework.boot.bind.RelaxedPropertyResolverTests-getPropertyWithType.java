@Test public void getPropertyWithType() throws Exception {
  assertThat(this.resolver.getProperty("my-integer",Integer.class),equalTo(123));
  assertThat(this.resolver.getProperty("my-missing",Integer.class),nullValue());
}
