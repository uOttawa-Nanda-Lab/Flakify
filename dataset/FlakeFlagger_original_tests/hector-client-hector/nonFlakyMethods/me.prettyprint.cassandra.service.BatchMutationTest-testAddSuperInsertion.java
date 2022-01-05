@Test public void testAddSuperInsertion(){
  SuperColumn sc=new SuperColumn(StringSerializer.get().toByteBuffer("c_name"),Arrays.asList(new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis())));
  batchMutate.addSuperInsertion("key1",columnFamilies,sc);
  assertEquals(1,batchMutate.getMutationMap().get(StringSerializer.get().toByteBuffer("key1")).size());
  SuperColumn sc2=new SuperColumn(StringSerializer.get().toByteBuffer("c_name2"),Arrays.asList(new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis())));
  batchMutate.addSuperInsertion("key1",columnFamilies,sc2);
  assertEquals(2,batchMutate.getMutationMap().get(StringSerializer.get().toByteBuffer("key1")).get("Standard1").size());
}
