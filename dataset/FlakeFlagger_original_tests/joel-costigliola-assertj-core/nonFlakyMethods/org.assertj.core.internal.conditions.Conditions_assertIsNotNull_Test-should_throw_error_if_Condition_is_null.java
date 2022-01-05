@Test public void should_throw_error_if_Condition_is_null(){
  thrown.expectNullPointerException("The condition to evaluate should not be null");
  conditions.assertIsNotNull(null);
}
