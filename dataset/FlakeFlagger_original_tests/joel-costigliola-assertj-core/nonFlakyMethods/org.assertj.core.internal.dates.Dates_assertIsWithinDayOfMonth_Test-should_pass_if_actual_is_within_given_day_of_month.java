@Test public void should_pass_if_actual_is_within_given_day_of_month(){
  dates.assertIsWithinDayOfMonth(someInfo(),actual,1);
}
