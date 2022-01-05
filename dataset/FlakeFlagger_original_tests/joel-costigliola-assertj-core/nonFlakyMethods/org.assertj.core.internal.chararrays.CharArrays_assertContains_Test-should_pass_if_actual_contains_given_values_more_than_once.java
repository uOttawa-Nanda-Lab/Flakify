@Test public void should_pass_if_actual_contains_given_values_more_than_once(){
  actual=arrayOf('a','b','c','c','b');
  arrays.assertContains(someInfo(),actual,arrayOf('b'));
}
