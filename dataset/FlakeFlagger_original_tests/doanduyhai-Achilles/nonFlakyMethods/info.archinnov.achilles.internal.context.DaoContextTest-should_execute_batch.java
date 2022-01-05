@Test public void should_execute_batch() throws Exception {
  BatchStatement batch=mock(BatchStatement.class);
  daoContext.executeBatch(batch);
  verify(session).execute(batch);
}
