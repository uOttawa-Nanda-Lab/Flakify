@Test public void getPropertyWithTypeAndDefault() throws Exception {
  assertThat(this.resolver.getProperty("my-integer",Integer.class,345),equalTo(123));
  assertThat(this.resolver.getProperty("my-missing",Integer.class,345),equalTo(345));
}
