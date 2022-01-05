@Test public void should_return_true_if_object_is_an_array(){
  assertTrue(Arrays.isArray(new String[0]));
  assertTrue(Arrays.isArray(new int[0]));
}
