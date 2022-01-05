@Test public void testEmpty(){
  Logger empty=lc.getLogger("");
  LoggerTestHelper.assertNameEquals(empty,"");
  LoggerTestHelper.assertLevels(null,empty,Level.DEBUG);
  Logger dot=lc.getLogger(".");
  LoggerTestHelper.assertNameEquals(dot,".");
  LoggerTestHelper.assertLevels(null,dot,Level.DEBUG);
  assertEquals(3,lc.getLoggerList().size());
}
