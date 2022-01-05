@Test public void iterator() throws Exception {
  this.managedDependencies.iterator();
  verify(this.delegate).iterator();
}
