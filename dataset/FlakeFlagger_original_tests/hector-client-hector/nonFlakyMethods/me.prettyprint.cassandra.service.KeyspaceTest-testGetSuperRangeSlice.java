@Test public void testGetSuperRangeSlice() throws HectorException {
  for (int i=0; i < 10; i++) {
    ColumnPath cp=new ColumnPath("Super1");
    cp.setSuper_column(bytes("SuperColumn_1"));
    cp.setColumn(bytes("testGetSuperRangeSlice_" + i));
    keyspace.insert("testGetSuperRangeSlice0",cp,StringSerializer.get().toByteBuffer("testGetSuperRangeSlice_Value_" + i));
    keyspace.insert("testGetSuperRangeSlice1",cp,StringSerializer.get().toByteBuffer("testGetSuperRangeSlice_Value_" + i));
  }
  ColumnParent clp=new ColumnParent("Super1");
  SliceRange sr=new SliceRange(ByteBuffer.wrap(new byte[0]),ByteBuffer.wrap(new byte[0]),false,150);
  SlicePredicate sp=new SlicePredicate();
  sp.setSlice_range(sr);
  ColumnPath cp=new ColumnPath("Super1");
  keyspace.remove("testGetSuperRangeSlice0",cp);
  keyspace.remove("testGetSuperRangeSlice1",cp);
}
