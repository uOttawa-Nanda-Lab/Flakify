@Test public void testLoggerMultipleChildren(){
  assertEquals(1,instanceCount());
  Logger xy0=lc.getLogger("x.y0");
  LoggerTestHelper.assertNameEquals(xy0,"x.y0");
  Logger xy1=lc.getLogger("x.y1");
  LoggerTestHelper.assertNameEquals(xy1,"x.y1");
  LoggerTestHelper.assertLevels(null,xy0,Level.DEBUG);
  LoggerTestHelper.assertLevels(null,xy1,Level.DEBUG);
  assertEquals(4,instanceCount());
  for (int i=0; i < 100; i++) {
    Logger xy_i=lc.getLogger("x.y" + i);
    LoggerTestHelper.assertNameEquals(xy_i,"x.y" + i);
    LoggerTestHelper.assertLevels(null,xy_i,Level.DEBUG);
  }
  assertEquals(102,instanceCount());
}
