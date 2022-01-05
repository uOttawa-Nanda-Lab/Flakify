@Test public void should_exception_when_achilles_counter_table_does_not_exist() throws Exception {
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage("The required generic table '" + CQL_COUNTER_TABLE + "' does not exist");
  creator.createTableForCounter(session,false);
}
