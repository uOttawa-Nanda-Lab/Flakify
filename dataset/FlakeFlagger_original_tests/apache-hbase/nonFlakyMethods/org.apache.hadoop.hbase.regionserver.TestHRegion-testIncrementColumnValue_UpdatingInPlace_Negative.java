public void testIncrementColumnValue_UpdatingInPlace_Negative() throws IOException {
  initHRegion(tableName,getName(),fam1);
  long value=3L;
  long amount=-1L;
  Put put=new Put(row);
  put.add(fam1,qual1,Bytes.toBytes(value));
  region.put(put);
  long result=region.incrementColumnValue(row,fam1,qual1,amount,true);
  assertEquals(value + amount,result);
  assertICV(row,fam1,qual1,value + amount);
}
