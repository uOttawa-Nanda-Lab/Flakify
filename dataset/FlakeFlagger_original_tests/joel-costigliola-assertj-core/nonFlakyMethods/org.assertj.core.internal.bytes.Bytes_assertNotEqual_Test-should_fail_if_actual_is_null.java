@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  bytes.assertNotEqual(someInfo(),null,(byte)8);
}
