@Test public void testEntryConditionsCheckNoLayout(){
  appender.setLayout(null);
  appender.checkEntryConditions();
  assertEquals(1,appender.getContext().getStatusManager().getCount());
}
