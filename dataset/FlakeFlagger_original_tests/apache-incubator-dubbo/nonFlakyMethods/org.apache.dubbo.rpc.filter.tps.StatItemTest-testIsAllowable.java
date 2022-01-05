@Test public void testIsAllowable() throws Exception {
  statItem=new StatItem("test",5,1000L);
  long lastResetTime=statItem.getLastResetTime();
  assertEquals(true,statItem.isAllowable());
  Thread.sleep(1100L);
  assertEquals(true,statItem.isAllowable());
  assertTrue(lastResetTime != statItem.getLastResetTime());
  assertEquals(4,statItem.getToken());
}
