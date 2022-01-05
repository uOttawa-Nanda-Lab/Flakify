@Test public void testConstruction(){
  LongSerializer le=LongSerializer.get();
  StringSerializer se=StringSerializer.get();
  ByteBufferSerializer be=ByteBufferSerializer.get();
  List<SuperColumn> tColumns=new ArrayList<SuperColumn>();
  SuperSlice<String,Long,ByteBuffer> slice=new SuperSliceImpl<String,Long,ByteBuffer>(tColumns,se,le,be);
  Assert.assertTrue(slice.getSuperColumns().isEmpty());
  Column c=new Column(le.toByteBuffer(5L),be.toByteBuffer(ByteBuffer.wrap(new byte[]{1})),2L);
  tColumns.add(new SuperColumn(se.toByteBuffer("super"),Arrays.asList(c)));
  slice=new SuperSliceImpl<String,Long,ByteBuffer>(tColumns,se,le,be);
  Assert.assertEquals(1,slice.getSuperColumns().size());
  Assert.assertEquals((Long)5L,slice.getColumnByName("super").get(0).getName());
}
