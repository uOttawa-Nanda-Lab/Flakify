public void testGet() throws Exception {
  byte[] row="row".getBytes();
  byte[] fam="fam".getBytes();
  byte[] qf1="qf1".getBytes();
  long ts=System.currentTimeMillis();
  int maxVersions=2;
  long lockid=5;
  RowLock rowLock=new RowLock(lockid);
  Get get=new Get(row,rowLock);
  get.addColumn(fam,qf1);
  get.setTimeRange(ts,ts + 1);
  get.setMaxVersions(maxVersions);
  byte[] sb=Writables.getBytes(get);
  Get desGet=(Get)Writables.getWritable(sb,new Get());
  assertTrue(Bytes.equals(get.getRow(),desGet.getRow()));
  Set<byte[]> set=null;
  Set<byte[]> desSet=null;
  for (  Map.Entry<byte[],NavigableSet<byte[]>> entry : get.getFamilyMap().entrySet()) {
    assertTrue(desGet.getFamilyMap().containsKey(entry.getKey()));
    set=entry.getValue();
    desSet=desGet.getFamilyMap().get(entry.getKey());
    for (    byte[] qualifier : set) {
      assertTrue(desSet.contains(qualifier));
    }
  }
  assertEquals(get.getLockId(),desGet.getLockId());
  assertEquals(get.getMaxVersions(),desGet.getMaxVersions());
  TimeRange tr=get.getTimeRange();
  TimeRange desTr=desGet.getTimeRange();
  assertEquals(tr.getMax(),desTr.getMax());
  assertEquals(tr.getMin(),desTr.getMin());
}
