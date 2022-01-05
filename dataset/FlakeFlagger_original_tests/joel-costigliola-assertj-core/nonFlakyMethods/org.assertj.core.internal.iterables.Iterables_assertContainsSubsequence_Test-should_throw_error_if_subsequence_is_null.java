@Test public void should_throw_error_if_subsequence_is_null(){
  thrown.expectNullPointerException(valuesToLookForIsNull());
  iterables.assertContainsSubsequence(someInfo(),actual,null);
}
