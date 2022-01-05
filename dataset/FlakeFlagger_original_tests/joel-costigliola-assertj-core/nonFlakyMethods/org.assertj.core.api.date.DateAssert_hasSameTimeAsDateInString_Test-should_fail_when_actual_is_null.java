@Test public void should_fail_when_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  assertThat((Date)null).hasSameTimeAs("2003-04-26T12:00:00");
}
