@Test public void should_create_table_with_speculative_retry_in_milli_secs() throws Exception {
  final String built=SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("name",DataType.text()).withOptions().speculativeRetry(TableOptions.SpeculativeRetryValue.millisecs(12)).build();
  assertThat(built).isEqualTo("\n\tCREATE TABLE test(\n\t\t" + "id bigint,\n\t\t" + "name text,\n\t\t"+ "PRIMARY KEY(id))\n\t"+ "WITH speculative_retry = '12ms'");
}
