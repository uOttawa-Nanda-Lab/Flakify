@Test public void should_create_AssertionError_if_error_is_thrown_when_creating_ComparisonFailure() throws Exception {
  when(createComparisonFailure()).thenThrow(new AssertionError("Thrown on purpose"));
  AssertionError error=factory.newAssertionError(description,new StandardRepresentation());
  check(error);
}
