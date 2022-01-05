@Test public void should_return_false_if_array_is_empty(){
  assertFalse(Arrays.hasOnlyNullElements(new String[0]));
}
