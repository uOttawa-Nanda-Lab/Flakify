@Test public void testMonth(){
  long now=1164045917522L;
  now=correctBasedOnTimeZone(now);
  long expected=1164931200000L;
  expected=correctBasedOnTimeZone(expected);
  long computed=TimeUtil.computeStartOfNextMonth(now);
  assertEquals(expected - now,1000 * (3600 * (5 + 24 * 10) + 60 * 54 + 42) + 478);
  assertEquals(expected,computed);
}
