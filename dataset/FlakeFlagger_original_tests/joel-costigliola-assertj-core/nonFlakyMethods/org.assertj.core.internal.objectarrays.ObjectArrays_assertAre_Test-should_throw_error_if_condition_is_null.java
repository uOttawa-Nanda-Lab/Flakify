@Test public void should_throw_error_if_condition_is_null(){
  thrown.expectNullPointerException("The condition to evaluate should not be null");
  arrays.assertAre(someInfo(),actual,null);
  verify(conditions).assertIsNotNull(null);
}
