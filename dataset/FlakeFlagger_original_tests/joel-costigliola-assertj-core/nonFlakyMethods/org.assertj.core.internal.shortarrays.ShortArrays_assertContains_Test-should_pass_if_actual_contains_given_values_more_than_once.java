@Test public void should_pass_if_actual_contains_given_values_more_than_once(){
  actual=arrayOf(6,8,10,10,8);
  arrays.assertContains(someInfo(),actual,arrayOf(8));
}
