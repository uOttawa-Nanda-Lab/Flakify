@Test public void testEntryConditionsCheck(){
  appender.checkEntryConditions();
  assertEquals(0,appender.getContext().getStatusManager().getCount());
}
