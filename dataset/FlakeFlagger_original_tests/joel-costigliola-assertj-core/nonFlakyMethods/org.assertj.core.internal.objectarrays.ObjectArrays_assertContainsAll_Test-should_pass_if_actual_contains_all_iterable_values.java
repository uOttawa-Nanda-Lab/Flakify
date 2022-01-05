@Test public void should_pass_if_actual_contains_all_iterable_values(){
  arrays.assertContainsAll(someInfo(),actual,newArrayList("Luke","Yoda","Leia"));
  arrays.assertContainsAll(someInfo(),actual,newArrayList("Luke","Yoda"));
  arrays.assertContainsAll(someInfo(),actual,newArrayList("Yoda","Luke"));
}
