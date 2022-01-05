/** 
 * Test merge tool.
 * @throws Exception
 */
public void testMergeTool() throws Exception {
  for (int i=0; i < regions.length; i++) {
    for (int j=0; j < rows[i].length; j++) {
      Get get=new Get(rows[i][j]);
      get.addFamily(FAMILY);
      Result result=regions[i].get(get,null);
      byte[] bytes=result.sorted()[0].getValue();
      assertNotNull(bytes);
      assertTrue(Bytes.equals(bytes,rows[i][j]));
    }
    regions[i].close();
    regions[i].getLog().closeAndDelete();
  }
  Path logPath=new Path("/tmp",HConstants.HREGION_LOGDIR_NAME + "_" + System.currentTimeMillis());
  LOG.info("Creating log " + logPath.toString());
  Path oldLogDir=new Path("/tmp",HConstants.HREGION_OLDLOGDIR_NAME);
  HLog log=new HLog(this.fs,logPath,oldLogDir,this.conf,null);
  try {
    HRegion merged=mergeAndVerify("merging regions 0 and 1",this.sourceRegions[0].getRegionNameAsString(),this.sourceRegions[1].getRegionNameAsString(),log,2);
    merged=mergeAndVerify("merging regions 0+1 and 2",merged.getRegionInfo().getRegionNameAsString(),this.sourceRegions[2].getRegionNameAsString(),log,3);
    merged=mergeAndVerify("merging regions 0+1+2 and 3",merged.getRegionInfo().getRegionNameAsString(),this.sourceRegions[3].getRegionNameAsString(),log,4);
    merged=mergeAndVerify("merging regions 0+1+2+3 and 4",merged.getRegionInfo().getRegionNameAsString(),this.sourceRegions[4].getRegionNameAsString(),log,rows.length);
  }
  finally {
    log.closeAndDelete();
  }
}
