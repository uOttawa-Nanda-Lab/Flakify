@Test public void should_pass_if_actual_contains_all_given_values(){
  arrays.assertContains(someInfo(),actual,arrayOf(6f,8f,10f));
}
