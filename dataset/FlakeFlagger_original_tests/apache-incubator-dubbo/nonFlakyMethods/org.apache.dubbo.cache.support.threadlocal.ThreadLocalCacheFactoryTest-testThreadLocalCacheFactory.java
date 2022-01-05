@Test public void testThreadLocalCacheFactory() throws Exception {
  Cache cache=super.constructCache();
  assertThat(cache instanceof ThreadLocalCache,is(true));
}
