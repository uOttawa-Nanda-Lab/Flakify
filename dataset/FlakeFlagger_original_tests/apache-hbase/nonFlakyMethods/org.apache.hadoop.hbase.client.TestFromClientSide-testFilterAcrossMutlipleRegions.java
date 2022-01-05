/** 
 * Test filters when multiple regions.  It does counts.  Needs eye-balling of logs to ensure that we're not scanning more regions that we're supposed to. Related to the TestFilterAcrossRegions over in the o.a.h.h.filter package.
 * @throws IOException
 */
@Test public void testFilterAcrossMutlipleRegions() throws IOException {
  byte[] name=Bytes.toBytes("testFilterAcrossMutlipleRegions");
  HTable t=TEST_UTIL.createTable(name,FAMILY);
  int rowCount=TEST_UTIL.loadTable(t,FAMILY);
  assertRowCount(t,rowCount);
  Map<HRegionInfo,HServerAddress> regions=splitTable(t);
  assertRowCount(t,rowCount);
  byte[] endKey=regions.keySet().iterator().next().getEndKey();
  int endKeyCount=countRows(t,createScanWithRowFilter(endKey));
  assertTrue(endKeyCount < rowCount);
  byte[] key=new byte[]{endKey[0],endKey[1],(byte)(endKey[2] + 1)};
  int plusOneCount=countRows(t,createScanWithRowFilter(key));
  assertEquals(endKeyCount + 1,plusOneCount);
  key=new byte[]{endKey[0],endKey[1],(byte)(endKey[2] + 2)};
  int plusTwoCount=countRows(t,createScanWithRowFilter(key));
  assertEquals(endKeyCount + 2,plusTwoCount);
  key=new byte[]{endKey[0],endKey[1],(byte)(endKey[2] - 1)};
  int minusOneCount=countRows(t,createScanWithRowFilter(key));
  assertEquals(endKeyCount - 1,minusOneCount);
  key=new byte[]{'a','a','a'};
  int countBBB=countRows(t,createScanWithRowFilter(key,null,CompareFilter.CompareOp.EQUAL));
  assertEquals(1,countBBB);
  int countGreater=countRows(t,createScanWithRowFilter(endKey,null,CompareFilter.CompareOp.GREATER_OR_EQUAL));
  assertEquals(0,countGreater);
  countGreater=countRows(t,createScanWithRowFilter(endKey,endKey,CompareFilter.CompareOp.GREATER_OR_EQUAL));
  assertEquals(rowCount - endKeyCount,countGreater);
}
