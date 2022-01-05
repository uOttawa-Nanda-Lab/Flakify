public void testIncrementColumnValue_AddingNew() throws IOException {
  initHRegion(tableName,getName(),fam1);
  long value=1L;
  long amount=3L;
  Put put=new Put(row);
  put.add(fam1,qual1,Bytes.toBytes(value));
  put.add(fam1,qual2,Bytes.toBytes(value));
  region.put(put);
  long result=region.incrementColumnValue(row,fam1,qual3,amount,true);
  assertEquals(amount,result);
  Get get=new Get(row);
  get.addColumn(fam1,qual3);
  Result rr=region.get(get,null);
  assertEquals(1,rr.size());
  assertICV(row,fam1,qual1,value);
  assertICV(row,fam1,qual2,value);
  assertICV(row,fam1,qual3,amount);
}
