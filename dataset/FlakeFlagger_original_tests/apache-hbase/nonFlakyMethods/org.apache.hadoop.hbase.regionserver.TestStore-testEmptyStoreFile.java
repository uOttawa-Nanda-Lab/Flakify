/** 
 * Test for hbase-1686.
 * @throws IOException
 */
public void testEmptyStoreFile() throws IOException {
  init(this.getName());
  this.store.add(new KeyValue(row,family,qf1,null));
  this.store.add(new KeyValue(row,family,qf2,null));
  flush(1);
  StoreFile f=this.store.getStorefiles().firstEntry().getValue();
  Path storedir=f.getPath().getParent();
  long seqid=f.getMaxSequenceId();
  HBaseConfiguration c=new HBaseConfiguration();
  FileSystem fs=FileSystem.get(c);
  Writer w=StoreFile.getWriter(fs,storedir);
  StoreFile.appendMetadata(w,seqid + 1);
  w.close();
  this.store.close();
  this.store=new Store(storedir.getParent().getParent(),this.store.getHRegion(),this.store.getFamily(),fs,null,c,null);
  System.out.println(this.store.getHRegionInfo().getEncodedName());
  assertEquals(2,this.store.getStorefilesCount());
  this.store.get(get,qualifiers,result);
  assertEquals(1,result.size());
}
