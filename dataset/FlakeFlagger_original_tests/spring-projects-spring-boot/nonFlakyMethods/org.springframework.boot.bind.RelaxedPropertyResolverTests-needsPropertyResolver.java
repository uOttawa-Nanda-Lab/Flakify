@Test public void needsPropertyResolver() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("PropertyResolver must not be null");
  new RelaxedPropertyResolver(null);
}
