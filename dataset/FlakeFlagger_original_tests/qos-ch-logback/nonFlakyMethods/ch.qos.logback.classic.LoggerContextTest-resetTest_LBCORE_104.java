@Test public void resetTest_LBCORE_104(){
  lc.putProperty("keyA","valA");
  lc.putObject("keyA","valA");
  assertEquals("valA",lc.getProperty("keyA"));
  assertEquals("valA",lc.getObject("keyA"));
  lc.reset();
  assertNull(lc.getProperty("keyA"));
  assertNull(lc.getObject("keyA"));
}
