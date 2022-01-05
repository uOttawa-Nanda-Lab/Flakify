@Test public void should_create_ArrayWrapperList_if_array_is_not_null(){
  int[] array={6,8};
  ArrayWrapperList list=ArrayWrapperList.wrap(array);
  assertNotNull(list);
  assertSame(array,list.array);
}
