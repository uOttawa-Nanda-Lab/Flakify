@Test public void should_return_true_if_array_has_only_null_elements(){
  String[] array={null,null};
  assertTrue(Arrays.hasOnlyNullElements(array));
}
