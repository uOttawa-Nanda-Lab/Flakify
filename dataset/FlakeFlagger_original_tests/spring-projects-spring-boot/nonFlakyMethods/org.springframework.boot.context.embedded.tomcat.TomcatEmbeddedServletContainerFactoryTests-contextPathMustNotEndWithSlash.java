@Test public void contextPathMustNotEndWithSlash() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ContextPath must start with '/ and not end with '/'");
  getFactory().setContextPath("extraslash/");
}
