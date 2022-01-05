@Test public void shouldNotDetectChangeAfterFirstRun() throws Exception {
  victim.check(cacheEntry);
  assertFalse(victim.getResourceChangeDetector().checkChangeForGroup(RESOURCE_URI,GROUP_NAME));
}
