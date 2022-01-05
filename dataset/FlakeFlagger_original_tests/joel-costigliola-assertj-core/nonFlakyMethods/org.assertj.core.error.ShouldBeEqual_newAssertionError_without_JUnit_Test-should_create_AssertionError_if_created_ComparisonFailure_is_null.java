@Test public void should_create_AssertionError_if_created_ComparisonFailure_is_null() throws Exception {
  when(createComparisonFailure()).thenReturn(null);
  AssertionError error=factory.newAssertionError(description,new StandardRepresentation());
  check(error);
}
