@Test public void should_pass_if_actual_contains_given_values_more_than_once(){
  actual=arrayOf(true,true,false,false);
  arrays.assertContains(someInfo(),actual,arrayOf(true));
}
