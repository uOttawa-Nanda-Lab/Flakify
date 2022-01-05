@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  maps.assertDoesNotContain(someInfo(),null,array(entry("job","Jedi")));
}
