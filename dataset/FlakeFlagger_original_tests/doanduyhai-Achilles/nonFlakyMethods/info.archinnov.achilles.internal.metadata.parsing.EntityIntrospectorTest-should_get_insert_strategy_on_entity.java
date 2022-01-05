@Test public void should_get_insert_strategy_on_entity() throws Exception {
  when(parsingContext.getDefaultInsertStrategy()).thenReturn(ALL_FIELDS);
  final InsertStrategy insertStrategy=introspector.getInsertStrategy(ComplexBean.class,parsingContext);
  assertThat(insertStrategy).isEqualTo(NOT_NULL_FIELDS);
}
