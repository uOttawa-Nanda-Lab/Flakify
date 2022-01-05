@Test public void testHour(){
  long now=1164045917522L;
  now=correctBasedOnTimeZone(now);
  long expected=1164049200000L;
  expected=correctBasedOnTimeZone(expected);
  long computed=TimeUtil.computeStartOfNextHour(now);
  assertEquals(expected - now,1000 * (42 + 60 * 54) + 478);
  assertEquals(expected,computed);
}
