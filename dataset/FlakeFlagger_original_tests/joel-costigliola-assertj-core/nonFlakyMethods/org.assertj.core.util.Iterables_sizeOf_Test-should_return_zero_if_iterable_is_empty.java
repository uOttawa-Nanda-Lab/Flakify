@Test public void should_return_zero_if_iterable_is_empty(){
  assertEquals(0,Iterables.sizeOf(new ArrayList<String>()));
}
