public void testGetFirstMetaRegionForRegionAfterMetaSplit() throws Exception {
  HTable meta=new HTable(HConstants.META_TABLE_NAME);
  HMaster master=this.cluster.getMaster();
  HServerAddress address=master.getMasterAddress();
  HTableDescriptor tableDesc=new HTableDescriptor(Bytes.toBytes("_MY_TABLE_"));
  HTableDescriptor metaTableDesc=meta.getTableDescriptor();
  byte[] startKey0=Bytes.toBytes("f");
  byte[] endKey0=Bytes.toBytes("h");
  HRegionInfo regionInfo0=new HRegionInfo(tableDesc,startKey0,endKey0);
  HRegionInfo metaRegionInfo0=new HRegionInfo(metaTableDesc,Bytes.toBytes(""),regionInfo0.getRegionName());
  MetaRegion meta0=new MetaRegion(address,metaRegionInfo0);
  byte[] startKey1=Bytes.toBytes("j");
  byte[] endKey1=Bytes.toBytes("m");
  HRegionInfo regionInfo1=new HRegionInfo(tableDesc,startKey1,endKey1);
  HRegionInfo metaRegionInfo1=new HRegionInfo(metaTableDesc,regionInfo0.getRegionName(),regionInfo1.getRegionName());
  MetaRegion meta1=new MetaRegion(address,metaRegionInfo1);
  HRegionInfo metaRegionInfo2=new HRegionInfo(metaTableDesc,regionInfo1.getRegionName(),Bytes.toBytes(""));
  MetaRegion meta2=new MetaRegion(address,metaRegionInfo2);
  byte[] startKeyX=Bytes.toBytes("h");
  byte[] endKeyX=Bytes.toBytes("j");
  HRegionInfo regionInfoX=new HRegionInfo(tableDesc,startKeyX,endKeyX);
  master.getRegionManager().offlineMetaRegionWithStartKey(startKey0);
  master.getRegionManager().putMetaRegionOnline(meta0);
  master.getRegionManager().putMetaRegionOnline(meta1);
  master.getRegionManager().putMetaRegionOnline(meta2);
  assertEquals(metaRegionInfo1.getStartKey(),master.getRegionManager().getFirstMetaRegionForRegion(regionInfoX).getStartKey());
  assertEquals(metaRegionInfo1.getRegionName(),master.getRegionManager().getFirstMetaRegionForRegion(regionInfoX).getRegionName());
}
