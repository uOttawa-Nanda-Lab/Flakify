@Test public void should_throws_NullPointerException_if_date_parameter_is_null(){
  thrown.expect(NullPointerException.class);
  dayOfWeekOf(null);
}
