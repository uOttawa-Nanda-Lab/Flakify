@Test public void should_return_values_of_private_field(){
  List<Integer> ages=fieldSupport.fieldValues("age",Integer.class,employees);
  assertEquals(newArrayList(800,26),ages);
}
