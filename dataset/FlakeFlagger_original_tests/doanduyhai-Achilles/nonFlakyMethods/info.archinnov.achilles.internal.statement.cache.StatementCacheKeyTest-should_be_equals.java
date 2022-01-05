@Test public void should_be_equals() throws Exception {
  StatementCacheKey key1=new StatementCacheKey(CacheType.SELECT_FIELD,Sets.newHashSet("field1","field2"),CompleteBean.class,withTimestamp(100L).ifNotExists());
  StatementCacheKey key2=new StatementCacheKey(CacheType.SELECT_FIELD,Sets.newHashSet("field2","field1"),CompleteBean.class,withTimestamp(100L).ifNotExists());
  assertThat(key1).isEqualTo(key2);
}
