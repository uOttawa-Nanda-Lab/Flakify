@Test public void testDataSourceDefaultsPreserved() throws Exception {
  this.context.register(CommonsDataSourceConfiguration.class);
  this.context.refresh();
  BasicDataSource ds=this.context.getBean(BasicDataSource.class);
  assertEquals(GenericObjectPool.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS,ds.getTimeBetweenEvictionRunsMillis());
  assertEquals(GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS,ds.getMinEvictableIdleTimeMillis());
  assertEquals(GenericObjectPool.DEFAULT_MAX_WAIT,ds.getMaxWait());
}
