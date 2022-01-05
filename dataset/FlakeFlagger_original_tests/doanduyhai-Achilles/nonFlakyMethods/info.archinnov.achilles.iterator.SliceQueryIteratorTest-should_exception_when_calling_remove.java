@Test(expected=UnsupportedOperationException.class) public void should_exception_when_calling_remove() throws Exception {
  sliceIterator.remove();
}
