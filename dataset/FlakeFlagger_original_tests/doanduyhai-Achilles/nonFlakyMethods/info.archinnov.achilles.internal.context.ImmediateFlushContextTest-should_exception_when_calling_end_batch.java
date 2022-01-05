@Test(expected=UnsupportedOperationException.class) public void should_exception_when_calling_end_batch() throws Exception {
  context.endBatch();
}
