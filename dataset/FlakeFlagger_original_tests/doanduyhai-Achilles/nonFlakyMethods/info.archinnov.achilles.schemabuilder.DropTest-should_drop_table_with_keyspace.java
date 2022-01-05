@Test public void should_drop_table_with_keyspace() throws Exception {
  final String built=SchemaBuilder.dropTable("ks","test").build();
  assertThat(built).isEqualTo("DROP TABLE ks.test");
}
