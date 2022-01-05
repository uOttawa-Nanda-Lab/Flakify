@Test public void should_determine_schema_update_status_for_table_from_default() throws Exception {
  when(configContext.getEnableSchemaUpdateForTables()).thenReturn(ImmutableMap.of("another_table",false));
  when(configContext.isEnableSchemaUpdate()).thenReturn(true);
  final boolean actual=parsingContext.isSchemaUpdateEnabled("table");
  assertThat(actual).isTrue();
}
