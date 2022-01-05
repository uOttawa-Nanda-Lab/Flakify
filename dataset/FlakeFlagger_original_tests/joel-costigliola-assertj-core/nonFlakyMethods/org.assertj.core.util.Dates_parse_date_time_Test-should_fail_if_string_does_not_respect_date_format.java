@Test public void should_fail_if_string_does_not_respect_date_format(){
  thrown.expect(RuntimeException.class);
  assertNull(parseDatetime("invalid date format"));
}
