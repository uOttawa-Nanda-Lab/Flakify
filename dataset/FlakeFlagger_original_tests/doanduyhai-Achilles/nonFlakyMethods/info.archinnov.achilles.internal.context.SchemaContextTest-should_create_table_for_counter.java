@Test public void should_create_table_for_counter() throws Exception {
  context.createTableForCounter();
  verify(tableCreator).createTableForCounter(session,true);
}
