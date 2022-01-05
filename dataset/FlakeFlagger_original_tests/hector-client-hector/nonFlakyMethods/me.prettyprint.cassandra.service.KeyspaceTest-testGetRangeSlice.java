@Test public void testGetRangeSlice() throws HectorException {
  for (int i=0; i < 10; i++) {
    ColumnPath cp=new ColumnPath("Standard2");
    cp.setColumn(bytes("testGetRangeSlice_" + i));
    keyspace.insert("testGetRangeSlice0",cp,StringSerializer.get().toByteBuffer("testGetRangeSlice_Value_" + i));
    keyspace.insert("testGetRangeSlice1",cp,StringSerializer.get().toByteBuffer("testGetRangeSlice_Value_" + i));
    keyspace.insert("testGetRangeSlice2",cp,StringSerializer.get().toByteBuffer("testGetRangeSlice_Value_" + i));
  }
  ColumnParent clp=new ColumnParent("Standard2");
  SliceRange sr=new SliceRange(ByteBuffer.wrap(new byte[0]),ByteBuffer.wrap(new byte[0]),false,150);
  SlicePredicate sp=new SlicePredicate();
  sp.setSlice_range(sr);
  ColumnPath cp=new ColumnPath("Standard2");
  keyspace.remove("testGetRanageSlice0",cp);
  keyspace.remove("testGetRanageSlice1",cp);
  keyspace.remove("testGetRanageSlice2",cp);
}
