@Test public void should_drop_table() throws Exception {
  final String built=SchemaBuilder.dropTable("test").build();
  assertThat(built).isEqualTo("DROP TABLE test");
}
