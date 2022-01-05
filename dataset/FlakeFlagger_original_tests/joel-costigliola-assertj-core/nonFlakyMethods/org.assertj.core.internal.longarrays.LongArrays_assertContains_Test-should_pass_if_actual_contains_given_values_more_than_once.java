@Test public void should_pass_if_actual_contains_given_values_more_than_once(){
  actual=arrayOf(6L,8L,10L,10L,8L);
  arrays.assertContains(someInfo(),actual,arrayOf(8L));
}
