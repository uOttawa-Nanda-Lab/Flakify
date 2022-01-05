@Test public void endOfLivedAppenderShouldBeAvailableDuringLingeringPeriod(){
  Appender a=appenderTracker.getOrCreate(key,now);
  appenderTracker.endOfLife(key);
  appenderTracker.removeStaleComponents(now);
  Appender lingering=appenderTracker.getOrCreate(key,now);
  assertTrue(lingering.isStarted());
  assertTrue(a == lingering);
  now+=AppenderTracker.LINGERING_TIMEOUT + 1;
  appenderTracker.removeStaleComponents(now);
  assertFalse(a.isStarted());
  a=appenderTracker.find(key);
  assertNull(a);
}
