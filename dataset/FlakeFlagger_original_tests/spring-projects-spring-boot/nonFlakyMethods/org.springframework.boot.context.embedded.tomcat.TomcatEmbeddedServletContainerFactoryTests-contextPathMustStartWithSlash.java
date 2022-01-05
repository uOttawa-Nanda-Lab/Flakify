@Test public void contextPathMustStartWithSlash() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ContextPath must start with '/ and not end with '/'");
  getFactory().setContextPath("missingslash");
}
