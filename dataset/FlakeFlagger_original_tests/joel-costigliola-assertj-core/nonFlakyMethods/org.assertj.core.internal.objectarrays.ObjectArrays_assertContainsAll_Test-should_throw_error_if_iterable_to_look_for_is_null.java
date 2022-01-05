@Test public void should_throw_error_if_iterable_to_look_for_is_null(){
  thrown.expectNullPointerException(iterableToLookForIsNull());
  arrays.assertContainsAll(someInfo(),actual,null);
}
