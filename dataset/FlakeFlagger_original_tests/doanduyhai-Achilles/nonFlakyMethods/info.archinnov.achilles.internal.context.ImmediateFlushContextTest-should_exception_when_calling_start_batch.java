@Test(expected=UnsupportedOperationException.class) public void should_exception_when_calling_start_batch() throws Exception {
  context.startBatch();
}
