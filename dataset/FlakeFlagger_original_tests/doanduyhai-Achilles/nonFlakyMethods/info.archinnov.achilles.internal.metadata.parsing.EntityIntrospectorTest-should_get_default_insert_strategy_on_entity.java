@Test public void should_get_default_insert_strategy_on_entity() throws Exception {
  when(parsingContext.getDefaultInsertStrategy()).thenReturn(ALL_FIELDS);
  final InsertStrategy insertStrategy=introspector.getInsertStrategy(Bean.class,parsingContext);
  assertThat(insertStrategy).isEqualTo(ALL_FIELDS);
}
