@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  doubles.assertEqual(someInfo(),null,8d);
}
