/** 
 * Test basic stop row filter works.
 * @throws Exception
 */
public void testStopRow() throws Exception {
  byte[] startrow=Bytes.toBytes("bbb");
  byte[] stoprow=Bytes.toBytes("ccc");
  try {
    this.r=createNewHRegion(REGION_INFO.getTableDesc(),null,null);
    addContent(this.r,HConstants.CATALOG_FAMILY);
    List<KeyValue> results=new ArrayList<KeyValue>();
    Scan scan=new Scan(Bytes.toBytes("abc"),Bytes.toBytes("abd"));
    scan.addFamily(HConstants.CATALOG_FAMILY);
    InternalScanner s=r.getScanner(scan);
    int count=0;
    while (s.next(results)) {
      count++;
    }
    s.close();
    assertEquals(0,count);
    scan=new Scan(startrow,stoprow);
    scan.addFamily(HConstants.CATALOG_FAMILY);
    s=r.getScanner(scan);
    count=0;
    KeyValue kv=null;
    results=new ArrayList<KeyValue>();
    for (boolean first=true; s.next(results); ) {
      kv=results.get(0);
      if (first) {
        assertTrue(Bytes.BYTES_COMPARATOR.compare(startrow,kv.getRow()) == 0);
        first=false;
      }
      count++;
    }
    assertTrue(Bytes.BYTES_COMPARATOR.compare(stoprow,kv.getRow()) > 0);
    assertTrue(count > 10);
    s.close();
  }
  finally {
    this.r.close();
    this.r.getLog().closeAndDelete();
    shutdownDfs(this.cluster);
  }
}
