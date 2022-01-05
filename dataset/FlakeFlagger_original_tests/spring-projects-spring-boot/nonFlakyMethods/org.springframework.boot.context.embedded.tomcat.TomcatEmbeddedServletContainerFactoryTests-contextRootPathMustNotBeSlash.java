@Test public void contextRootPathMustNotBeSlash() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Root ContextPath must be specified using an empty string");
  getFactory().setContextPath("/");
}
