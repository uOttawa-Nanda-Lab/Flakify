public void testMerge() throws IOException {
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
      Path oldRegionPath=region.getRegionDir();
      long startTime=System.currentTimeMillis();
      HRegion subregions[]=region.splitRegion(splitRow);
      if (subregions != null) {
        LOG.info("Split region elapsed time: " + ((System.currentTimeMillis() - startTime) / 1000.0));
        assertEquals("Number of subregions",subregions.length,2);
        for (int i=0; i < subregions.length; i++) {
          subregions[i]=openClosedRegion(subregions[i]);
          subregions[i].compactStores();
        }
        Path oldRegion1=subregions[0].getRegionDir();
        Path oldRegion2=subregions[1].getRegionDir();
        startTime=System.currentTimeMillis();
        region=HRegion.mergeAdjacent(subregions[0],subregions[1]);
        LOG.info("Merge regions elapsed time: " + ((System.currentTimeMillis() - startTime) / 1000.0));
        fs.delete(oldRegion1,true);
        fs.delete(oldRegion2,true);
        fs.delete(oldRegionPath,true);
      }
      LOG.info("splitAndMerge completed.");
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
