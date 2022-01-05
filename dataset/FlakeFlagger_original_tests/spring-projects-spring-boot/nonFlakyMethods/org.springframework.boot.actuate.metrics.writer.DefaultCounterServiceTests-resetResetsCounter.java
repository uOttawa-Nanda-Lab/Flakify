@Test public void resetResetsCounter() throws Exception {
  this.service.reset("foo");
  verify(this.repository).reset("counter.foo");
}
