@Test public void should_determine_schema_update_status_for_table_from_map() throws Exception {
  when(configContext.getEnableSchemaUpdateForTables()).thenReturn(ImmutableMap.of("table",false));
  final boolean actual=parsingContext.isSchemaUpdateEnabled("table");
  assertThat(actual).isFalse();
}
