public void testIncrementColumnValue_UpdatingInPlace() throws IOException {
  initHRegion(tableName,getName(),fam1);
  long value=1L;
  long amount=3L;
  Put put=new Put(row);
  put.add(fam1,qual1,Bytes.toBytes(value));
  region.put(put);
  long result=region.incrementColumnValue(row,fam1,qual1,amount,true);
  assertEquals(value + amount,result);
  Store store=region.getStore(fam1);
  assertEquals(1,store.memstore.kvset.size());
  assertTrue(store.memstore.snapshot.isEmpty());
  assertICV(row,fam1,qual1,value + amount);
}
