@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  iterables.assertContainsOnlyOnce(someInfo(),null,array("Yoda"));
}
