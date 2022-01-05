@Test public void should_pass_within_time_constraints(){
  List<String> generated=new ArrayList<>(GENERATED_OBJECTS_NUMBER);
  for (int count=0; count < GENERATED_OBJECTS_NUMBER; count++) {
    generated.add(UUID.randomUUID().toString());
  }
  long time=System.currentTimeMillis();
  iterables.assertDoesNotHaveDuplicates(someInfo(),generated);
  System.out.println("Time elapsed in ms for assertDoesNotHaveDuplicates : " + (System.currentTimeMillis() - time));
  assertThat((System.currentTimeMillis() - time)).isLessThan(2000);
}
