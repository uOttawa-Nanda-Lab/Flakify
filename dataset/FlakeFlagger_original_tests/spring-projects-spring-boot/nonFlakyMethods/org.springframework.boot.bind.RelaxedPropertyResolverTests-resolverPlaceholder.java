@Test public void resolverPlaceholder() throws Exception {
  this.thrown.expect(UnsupportedOperationException.class);
  this.resolver.resolvePlaceholders("test");
}
