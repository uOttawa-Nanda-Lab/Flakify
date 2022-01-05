@Test public void should_drop_table_with_keyspace_if_exists() throws Exception {
  final String built=SchemaBuilder.dropTable("ks","test").ifExists(true).build();
  assertThat(built).isEqualTo("DROP TABLE IF EXISTS ks.test");
}
