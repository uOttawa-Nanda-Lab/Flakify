@Test public void should_pass_if_actual_contains_given_values_more_than_once(){
  actual=arrayOf(6d,8d,10d,10d,8d);
  arrays.assertContains(someInfo(),actual,arrayOf(8d));
}
