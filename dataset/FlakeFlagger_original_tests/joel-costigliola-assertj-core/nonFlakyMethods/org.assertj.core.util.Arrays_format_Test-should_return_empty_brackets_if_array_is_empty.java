@Test public void should_return_empty_brackets_if_array_is_empty(){
  final Object[] array=new Object[0];
  assertEquals("[]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[]",Arrays.format(array));
}
