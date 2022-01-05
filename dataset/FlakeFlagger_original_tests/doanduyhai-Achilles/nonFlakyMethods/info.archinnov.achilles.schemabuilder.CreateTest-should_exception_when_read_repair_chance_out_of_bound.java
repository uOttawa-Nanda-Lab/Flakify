@Test(expected=IllegalArgumentException.class) public void should_exception_when_read_repair_chance_out_of_bound() throws Exception {
  SchemaBuilder.createTable("test").addPartitionKey("id",DataType.bigint()).addColumn("name",DataType.text()).withOptions().readRepairChance(1.3).build();
}
