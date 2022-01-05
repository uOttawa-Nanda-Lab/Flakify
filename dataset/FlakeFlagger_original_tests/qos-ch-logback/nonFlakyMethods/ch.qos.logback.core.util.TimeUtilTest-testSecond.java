@Test public void testSecond(){
  long now=1164042317522L;
  long expected=1164042318000L;
  long computed=TimeUtil.computeStartOfNextSecond(now);
  assertEquals(expected - now,478);
  assertEquals(expected,computed);
}
