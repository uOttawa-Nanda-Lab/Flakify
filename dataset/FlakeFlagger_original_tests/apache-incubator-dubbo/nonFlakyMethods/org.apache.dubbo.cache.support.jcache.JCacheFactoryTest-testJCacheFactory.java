@Test public void testJCacheFactory() throws Exception {
  Cache cache=super.constructCache();
  assertThat(cache instanceof JCache,is(true));
}
