@Test public void shouldNotFailWhenReloadingCacheOutsideOfContext() throws Exception {
  Context.unset();
  victim.onCachePeriodChanged(0);
}
