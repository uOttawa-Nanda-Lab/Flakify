@Test public void getPropertyAsClass() throws Exception {
  assertThat(this.resolver.getPropertyAsClass("my-class",String.class),equalTo(String.class));
  assertThat(this.resolver.getPropertyAsClass("my-missing",String.class),nullValue());
}
