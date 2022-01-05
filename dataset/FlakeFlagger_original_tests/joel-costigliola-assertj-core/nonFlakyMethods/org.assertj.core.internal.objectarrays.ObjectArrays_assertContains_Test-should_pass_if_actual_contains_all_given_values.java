@Test public void should_pass_if_actual_contains_all_given_values(){
  arrays.assertContains(someInfo(),actual,array("Luke","Yoda","Leia"));
}
