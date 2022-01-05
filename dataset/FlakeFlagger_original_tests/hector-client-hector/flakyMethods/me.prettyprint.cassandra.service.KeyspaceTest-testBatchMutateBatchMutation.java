@Test public void testBatchMutateBatchMutation() throws HectorException {
  BatchMutation<String> batchMutation=new BatchMutation<String>(StringSerializer.get());
  List<String> columnFamilies=Arrays.asList("Standard1");
  for (int i=0; i < 10; i++) {
    for (int j=0; j < 10; j++) {
      Column col=new Column(StringSerializer.get().toByteBuffer("testBatchMutateColumn_" + j),StringSerializer.get().toByteBuffer("testBatchMutateColumn_value_" + j),connectionManager.createClock());
      batchMutation.addInsertion("testBatchMutateColumn_" + i,columnFamilies,col);
    }
  }
  keyspace.batchMutate(batchMutation);
  for (int i=0; i < 10; i++) {
    for (int j=0; j < 10; j++) {
      ColumnPath cp=new ColumnPath("Standard1");
      cp.setColumn(bytes("testBatchMutateColumn_" + j));
      Column col=keyspace.getColumn("testBatchMutateColumn_" + i,cp);
      assertNotNull(col);
      String value=string(col.getValue());
      assertEquals("testBatchMutateColumn_value_" + j,value);
    }
  }
  batchMutation=new BatchMutation<String>(StringSerializer.get());
  for (int i=0; i < 10; i++) {
    SlicePredicate slicePredicate=new SlicePredicate();
    for (int j=0; j < 10; j++) {
      slicePredicate.addToColumn_names(StringSerializer.get().toByteBuffer("testBatchMutateColumn_" + j));
    }
    Deletion deletion=new Deletion(connectionManager.createClock());
    deletion.setPredicate(slicePredicate);
    batchMutation.addDeletion("testBatchMutateColumn_" + i,columnFamilies,deletion);
  }
  keyspace.batchMutate(batchMutation);
  for (int i=0; i < 10; i++) {
    for (int j=0; j < 10; j++) {
      ColumnPath cp=new ColumnPath("Standard1");
      cp.setColumn(bytes("testBatchMutateColumn_" + j));
      try {
        keyspace.getColumn("testBatchMutateColumn_" + i,cp);
        fail();
      }
 catch (      HNotFoundException e) {
      }
    }
  }
}
