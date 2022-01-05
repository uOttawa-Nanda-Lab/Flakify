/** 
 * Splits twice and verifies getting from each of the split regions.
 * @throws Exception
 */
public void testBasicSplit() throws Exception {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[][] families={fam1,fam2,fam3};
  HBaseConfiguration hc=initSplit();
  String method=this.getName();
  initHRegion(tableName,method,hc,families);
  try {
    LOG.info("" + addContent(region,fam3));
    region.flushcache();
    byte[] splitRow=region.compactStores();
    assertNotNull(splitRow);
    LOG.info("SplitRow: " + Bytes.toString(splitRow));
    HRegion[] regions=split(region,splitRow);
    try {
      for (int i=0; i < regions.length; i++) {
        regions[i]=openClosedRegion(regions[i]);
      }
      assertGet(regions[0],fam3,Bytes.toBytes(START_KEY));
      assertGet(regions[1],fam3,splitRow);
      assertScan(regions[0],fam3,Bytes.toBytes(START_KEY));
      assertScan(regions[1],fam3,splitRow);
      for (int i=0; i < regions.length; i++) {
        for (int j=0; j < 2; j++) {
          addContent(regions[i],fam3);
        }
        addContent(regions[i],fam2);
        addContent(regions[i],fam1);
        regions[i].flushcache();
      }
      byte[][] midkeys=new byte[regions.length][];
      for (int i=0; i < regions.length; i++) {
        midkeys[i]=regions[i].compactStores();
      }
      TreeMap<String,HRegion> sortedMap=new TreeMap<String,HRegion>();
      for (int i=0; i < regions.length; i++) {
        HRegion[] rs=null;
        if (midkeys[i] != null) {
          rs=split(regions[i],midkeys[i]);
          for (int j=0; j < rs.length; j++) {
            sortedMap.put(Bytes.toString(rs[j].getRegionName()),openClosedRegion(rs[j]));
          }
        }
      }
      LOG.info("Made 4 regions");
      int interval=(LAST_CHAR - FIRST_CHAR) / 3;
      byte[] b=Bytes.toBytes(START_KEY);
      for (      HRegion r : sortedMap.values()) {
        assertGet(r,fam3,b);
        b[0]+=interval;
      }
    }
  finally {
      for (int i=0; i < regions.length; i++) {
        try {
          regions[i].close();
        }
 catch (        IOException e) {
        }
      }
    }
  }
  finally {
    if (region != null) {
      region.close();
      region.getLog().closeAndDelete();
    }
  }
}
