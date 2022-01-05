@Test public void should_return_empty_array_if_type_array_is_empty(){
  assertEquals(Objects.namesOf(new Class<?>[0]).length,0);
}
