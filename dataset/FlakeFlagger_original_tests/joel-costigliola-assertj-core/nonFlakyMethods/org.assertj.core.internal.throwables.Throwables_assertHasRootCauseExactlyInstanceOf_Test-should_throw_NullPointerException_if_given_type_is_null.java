@Test public void should_throw_NullPointerException_if_given_type_is_null() throws Exception {
  thrown.expectNullPointerException("The given type should not be null");
  throwables.assertHasRootCauseExactlyInstanceOf(someInfo(),throwableWithCause,null);
}
