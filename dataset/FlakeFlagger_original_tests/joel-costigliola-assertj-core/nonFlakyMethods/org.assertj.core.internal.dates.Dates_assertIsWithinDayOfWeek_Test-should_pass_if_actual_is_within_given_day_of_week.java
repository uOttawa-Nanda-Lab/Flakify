@Test public void should_pass_if_actual_is_within_given_day_of_week(){
  dates.assertIsWithinDayOfWeek(someInfo(),actual,SATURDAY);
}
