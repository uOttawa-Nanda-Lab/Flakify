@Test public void should_create_index() throws Exception {
  final String statement=SchemaBuilder.createIndex("myIndex").ifNotExists().onTable("ks","test").andColumn("col");
  assertThat(statement).isEqualTo("\n\tCREATE INDEX IF NOT EXISTS myIndex ON ks.test(col)");
}
