@Test public void should_return_empty_array_if_type_array_is_null(){
  assertEquals(Objects.namesOf((Class<?>[])null).length,0);
}
