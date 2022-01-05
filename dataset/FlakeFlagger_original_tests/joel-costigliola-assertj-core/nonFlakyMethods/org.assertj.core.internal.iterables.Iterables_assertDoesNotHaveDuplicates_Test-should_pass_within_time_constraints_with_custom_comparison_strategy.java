@Test public void should_pass_within_time_constraints_with_custom_comparison_strategy(){
  List<String> generated=new ArrayList<>(GENERATED_OBJECTS_NUMBER);
  for (int count=0; count < GENERATED_OBJECTS_NUMBER; count++) {
    generated.add(UUID.randomUUID().toString());
  }
  long time=System.currentTimeMillis();
  iterablesWithCaseInsensitiveComparisonStrategy.assertDoesNotHaveDuplicates(someInfo(),generated);
  System.out.println("Time elapsed in ms for assertDoesNotHaveDuplicates with custom comparison strategy : " + (System.currentTimeMillis() - time));
  assertThat((System.currentTimeMillis() - time)).isLessThan(10000);
}
