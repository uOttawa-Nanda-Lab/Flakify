public void testIncrementColumnValue_AddingNewAfterSFCheck() throws IOException {
  initHRegion(tableName,getName(),fam1);
  long value=1L;
  long amount=3L;
  Put put=new Put(row);
  put.add(fam1,qual1,Bytes.toBytes(value));
  put.add(fam1,qual2,Bytes.toBytes(value));
  region.put(put);
  region.flushcache();
  Store store=region.getStore(fam1);
  assertEquals(0,store.memstore.kvset.size());
  long r=region.incrementColumnValue(row,fam1,qual3,amount,true);
  assertEquals(amount,r);
  assertICV(row,fam1,qual3,amount);
  region.flushcache();
  assertICV(row,fam1,qual3,amount);
}
