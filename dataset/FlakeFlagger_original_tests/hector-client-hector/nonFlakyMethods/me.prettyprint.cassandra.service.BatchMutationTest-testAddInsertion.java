@Test public void testAddInsertion(){
  Column column=new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis());
  batchMutate.addInsertion("key1",columnFamilies,column);
  Map<ByteBuffer,Map<String,List<Mutation>>> mutationMap=batchMutate.getMutationMap();
  assertEquals(1,mutationMap.get(StringSerializer.get().toByteBuffer("key1")).size());
  Column column2=new Column(StringSerializer.get().toByteBuffer("c_name2"),StringSerializer.get().toByteBuffer("c_val2"),System.currentTimeMillis());
  batchMutate.addInsertion("key1",columnFamilies,column2);
  assertEquals(2,mutationMap.get(StringSerializer.get().toByteBuffer("key1")).get("Standard1").size());
}
