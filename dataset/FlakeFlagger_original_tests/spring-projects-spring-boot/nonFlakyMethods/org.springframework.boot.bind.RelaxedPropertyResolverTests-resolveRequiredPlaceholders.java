@Test public void resolveRequiredPlaceholders() throws Exception {
  this.thrown.expect(UnsupportedOperationException.class);
  this.resolver.resolveRequiredPlaceholders("test");
}
