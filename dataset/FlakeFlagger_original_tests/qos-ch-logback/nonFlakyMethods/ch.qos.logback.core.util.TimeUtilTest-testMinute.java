@Test public void testMinute(){
  long now=1164042317522L;
  long expected=1164042360000L;
  long computed=TimeUtil.computeStartOfNextMinute(now);
  assertEquals(expected - now,1000 * 42 + 478);
  assertEquals(expected,computed);
}
