public void testScan() throws Exception {
  byte[] startRow="startRow".getBytes();
  byte[] stopRow="stopRow".getBytes();
  byte[] fam="fam".getBytes();
  byte[] qf1="qf1".getBytes();
  long ts=System.currentTimeMillis();
  int maxVersions=2;
  Scan scan=new Scan(startRow,stopRow);
  scan.addColumn(fam,qf1);
  scan.setTimeRange(ts,ts + 1);
  scan.setMaxVersions(maxVersions);
  byte[] sb=Writables.getBytes(scan);
  Scan desScan=(Scan)Writables.getWritable(sb,new Scan());
  assertTrue(Bytes.equals(scan.getStartRow(),desScan.getStartRow()));
  assertTrue(Bytes.equals(scan.getStopRow(),desScan.getStopRow()));
  assertEquals(scan.getCacheBlocks(),desScan.getCacheBlocks());
  Set<byte[]> set=null;
  Set<byte[]> desSet=null;
  for (  Map.Entry<byte[],NavigableSet<byte[]>> entry : scan.getFamilyMap().entrySet()) {
    assertTrue(desScan.getFamilyMap().containsKey(entry.getKey()));
    set=entry.getValue();
    desSet=desScan.getFamilyMap().get(entry.getKey());
    for (    byte[] column : set) {
      assertTrue(desSet.contains(column));
    }
    scan=new Scan(startRow);
    byte[] prefix=Bytes.toBytes(getName());
    scan.setFilter(new PrefixFilter(prefix));
    sb=Writables.getBytes(scan);
    desScan=(Scan)Writables.getWritable(sb,new Scan());
    Filter f=desScan.getFilter();
    assertTrue(f instanceof PrefixFilter);
  }
  assertEquals(scan.getMaxVersions(),desScan.getMaxVersions());
  TimeRange tr=scan.getTimeRange();
  TimeRange desTr=desScan.getTimeRange();
  assertEquals(tr.getMax(),desTr.getMax());
  assertEquals(tr.getMin(),desTr.getMin());
}
