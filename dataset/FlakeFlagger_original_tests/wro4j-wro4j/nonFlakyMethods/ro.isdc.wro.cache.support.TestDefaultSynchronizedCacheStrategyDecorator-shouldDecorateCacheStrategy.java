@Test public void shouldDecorateCacheStrategy(){
  final CacheStrategy<CacheKey,CacheValue> original=new LruMemoryCacheStrategy<CacheKey,CacheValue>();
  victim=DefaultSynchronizedCacheStrategyDecorator.decorate(original);
  Assert.assertTrue(victim instanceof DefaultSynchronizedCacheStrategyDecorator);
  Assert.assertSame(original,((ObjectDecorator<?>)victim).getDecoratedObject());
}
