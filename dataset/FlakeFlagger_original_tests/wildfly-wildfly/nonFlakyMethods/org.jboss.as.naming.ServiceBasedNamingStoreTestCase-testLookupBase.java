@Test public void testLookupBase() throws Exception {
  final Object obj=store.lookup(new CompositeName());
  assertNotNull(obj);
}
