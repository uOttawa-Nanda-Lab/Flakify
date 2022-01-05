@Test public void testCache() throws Exception {
  testCache("lru");
  testCache("threadlocal");
}
