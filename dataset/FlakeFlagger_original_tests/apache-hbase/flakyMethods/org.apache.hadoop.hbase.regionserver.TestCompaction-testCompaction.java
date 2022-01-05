/** 
 * Run compaction and flushing memstore Assert deletes get cleaned up.
 * @throws Exception
 */
public void testCompaction() throws Exception {
  createStoreFile(r);
  for (int i=0; i < COMPACTION_THRESHOLD; i++) {
    createStoreFile(r);
  }
  addContent(new HRegionIncommon(r),Bytes.toString(COLUMN_FAMILY));
  Result result=r.get(new Get(STARTROW).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertEquals(COMPACTION_THRESHOLD,result.size());
  r.flushcache();
  r.compactStores();
  assertTrue(this.cluster.getFileSystem().exists(this.compactionDir));
  assertTrue(!this.cluster.getFileSystem().exists(this.regionCompactionDir));
  byte[] secondRowBytes=START_KEY.getBytes(HConstants.UTF8_ENCODING);
  secondRowBytes[START_KEY_BYTES.length - 1]++;
  result=r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertEquals(3,result.size());
  Delete delete=new Delete(secondRowBytes,System.currentTimeMillis(),null);
  byte[][] famAndQf={COLUMN_FAMILY,null};
  delete.deleteFamily(famAndQf[0]);
  r.delete(delete,null,true);
  result=r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertTrue(result.isEmpty());
  r.flushcache();
  result=r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertTrue(result.isEmpty());
  createSmallerStoreFile(this.r);
  r.flushcache();
  result=r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertTrue(result.isEmpty());
  r.compactStores(true);
  assertEquals(r.getStore(COLUMN_FAMILY_TEXT).getStorefiles().size(),1);
  result=r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100),null);
  assertTrue(result.isEmpty());
  int count=0;
  boolean containsStartRow=false;
  for (  StoreFile f : this.r.stores.get(COLUMN_FAMILY_TEXT).getStorefiles().values()) {
    HFileScanner scanner=f.getReader().getScanner(false,false);
    scanner.seekTo();
    do {
      byte[] row=scanner.getKeyValue().getRow();
      if (Bytes.equals(row,STARTROW)) {
        containsStartRow=true;
        count++;
      }
 else {
        assertFalse(Bytes.equals(row,secondRowBytes));
      }
    }
 while (scanner.next());
  }
  assertTrue(containsStartRow);
  assertTrue(count == 3);
  final int ttlInSeconds=1;
  for (  Store store : this.r.stores.values()) {
    store.ttl=ttlInSeconds * 1000;
  }
  Thread.sleep(ttlInSeconds * 1000);
  r.compactStores(true);
  count=count();
  assertTrue(count == 0);
}
