@Test public void should_return_iterable_size(){
  List<String> list=newArrayList("Frodo","Sam");
  assertEquals(2,Iterables.sizeOf(list));
}
