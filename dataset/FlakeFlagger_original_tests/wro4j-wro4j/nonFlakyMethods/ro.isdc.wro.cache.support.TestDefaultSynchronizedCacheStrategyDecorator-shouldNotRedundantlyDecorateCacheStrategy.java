/** 
 * Fix Issue 528:  Redundant CacheStrategy decoration (which has unclear cause, but it is safe to prevent redundant decoration anyway).
 */
@Test public void shouldNotRedundantlyDecorateCacheStrategy(){
  final CacheStrategy<CacheKey,CacheValue> original=DefaultSynchronizedCacheStrategyDecorator.decorate(new LruMemoryCacheStrategy<CacheKey,CacheValue>());
  victim=DefaultSynchronizedCacheStrategyDecorator.decorate(original);
  Assert.assertTrue(victim instanceof DefaultSynchronizedCacheStrategyDecorator);
  Assert.assertSame(original,victim);
}
