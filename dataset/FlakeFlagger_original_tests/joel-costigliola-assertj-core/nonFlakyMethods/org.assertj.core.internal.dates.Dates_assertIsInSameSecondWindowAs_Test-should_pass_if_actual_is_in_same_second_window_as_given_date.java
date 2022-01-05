@Test public void should_pass_if_actual_is_in_same_second_window_as_given_date(){
  dates.assertIsInSameSecondWindowAs(someInfo(),actual,parseDatetimeWithMs("2011-01-01T03:15:05.999"));
  dates.assertIsInSameSecondWindowAs(someInfo(),actual,parseDatetimeWithMs("2011-01-01T03:15:05.001"));
  dates.assertIsInSameSecondWindowAs(someInfo(),actual,new Date(actual.getTime() - 1));
}
