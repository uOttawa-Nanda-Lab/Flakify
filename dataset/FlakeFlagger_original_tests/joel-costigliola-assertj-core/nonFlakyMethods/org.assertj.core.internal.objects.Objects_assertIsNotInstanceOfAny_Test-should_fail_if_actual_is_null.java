@Test public void should_fail_if_actual_is_null(){
  Class<?>[] types={Object.class};
  thrown.expectAssertionError(actualIsNull());
  objects.assertIsNotInstanceOfAny(someInfo(),null,types);
}
