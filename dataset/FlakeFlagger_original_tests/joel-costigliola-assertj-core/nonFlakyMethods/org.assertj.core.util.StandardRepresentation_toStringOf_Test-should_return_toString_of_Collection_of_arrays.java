@Test public void should_return_toString_of_Collection_of_arrays(){
  List<Boolean[]> collection=newArrayList(array(true,false),array(true,false,true));
  assertEquals("[[true, false], [true, false, true]]",new StandardRepresentation().toStringOf(collection));
}
