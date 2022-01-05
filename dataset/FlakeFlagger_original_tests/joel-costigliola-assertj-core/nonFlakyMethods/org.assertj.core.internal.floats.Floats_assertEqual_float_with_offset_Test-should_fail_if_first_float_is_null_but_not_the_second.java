@Test public void should_fail_if_first_float_is_null_but_not_the_second(){
  thrown.expectAssertionError(actualIsNull());
  floats.assertEqual(someInfo(),null,8f,offset(1f));
}
