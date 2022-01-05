@Test public void testLruCacheFactory() throws Exception {
  Cache cache=super.constructCache();
  assertThat(cache instanceof ExpiringCache,is(true));
}
