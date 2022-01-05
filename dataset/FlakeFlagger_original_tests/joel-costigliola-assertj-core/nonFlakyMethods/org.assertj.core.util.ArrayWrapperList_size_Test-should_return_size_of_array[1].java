@Test public void should_return_size_of_array(){
  ArrayWrapperList list=new ArrayWrapperList(array);
  assertEquals(array.length,list.size());
}
