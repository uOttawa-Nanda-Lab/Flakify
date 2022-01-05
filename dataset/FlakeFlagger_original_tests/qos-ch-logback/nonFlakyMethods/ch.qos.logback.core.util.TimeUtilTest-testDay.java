@Test public void testDay(){
  long now=1164045917522L;
  now=correctBasedOnTimeZone(now);
  long expected=1164067200000L;
  expected=correctBasedOnTimeZone(expected);
  long computed=TimeUtil.computeStartOfNextDay(now);
  assertEquals(expected - now,1000 * (3600 * 5 + 60 * 54 + 42) + 478);
  assertEquals(expected,computed);
}
