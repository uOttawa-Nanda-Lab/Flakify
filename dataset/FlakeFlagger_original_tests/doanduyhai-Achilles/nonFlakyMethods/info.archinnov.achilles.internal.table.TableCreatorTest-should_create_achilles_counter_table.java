@Test public void should_create_achilles_counter_table() throws Exception {
  creator.createTableForCounter(session,true);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE TABLE " + CQL_COUNTER_TABLE + "(\n"+ "\t\t"+ CQL_COUNTER_FQCN+ " text,\n"+ "\t\t"+ CQL_COUNTER_PRIMARY_KEY+ " text,\n"+ "\t\t"+ CQL_COUNTER_PROPERTY_NAME+ " text,\n"+ "\t\t"+ CQL_COUNTER_VALUE+ " counter,\n"+ "\t\tPRIMARY KEY(("+ CQL_COUNTER_FQCN+ ", "+ CQL_COUNTER_PRIMARY_KEY+ "), "+ CQL_COUNTER_PROPERTY_NAME+ "))\n"+ "\tWITH comment = 'Create default Achilles counter table \""+ CQL_COUNTER_TABLE+ "\"'");
}
