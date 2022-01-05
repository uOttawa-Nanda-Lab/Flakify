/** 
 * @throws Exception
 */
public void testScanMultipleVersions() throws Exception {
  HTable t=new HTable(conf,TABLE_NAME);
  for (int i=0; i < ROWS.length; i++) {
    for (int j=0; j < TIMESTAMPS.length; j++) {
      Get get=new Get(ROWS[i]);
      get.addFamily(HConstants.CATALOG_FAMILY);
      get.setTimeStamp(TIMESTAMPS[j]);
      Result result=t.get(get);
      int cellCount=0;
      for (      @SuppressWarnings("unused") KeyValue kv : result.sorted()) {
        cellCount++;
      }
      assertTrue(cellCount == 1);
    }
  }
  int count=0;
  Scan scan=new Scan();
  scan.addFamily(HConstants.CATALOG_FAMILY);
  ResultScanner s=t.getScanner(scan);
  try {
    for (Result rr=null; (rr=s.next()) != null; ) {
      System.out.println(rr.toString());
      count+=1;
    }
    assertEquals("Number of rows should be 2",2,count);
  }
  finally {
    s.close();
  }
  count=0;
  scan=new Scan();
  scan.setTimeRange(1000L,Long.MAX_VALUE);
  scan.addFamily(HConstants.CATALOG_FAMILY);
  s=t.getScanner(scan);
  try {
    while (s.next() != null) {
      count+=1;
    }
    assertEquals("Number of rows should be 2",2,count);
  }
  finally {
    s.close();
  }
  count=0;
  scan=new Scan();
  scan.setTimeStamp(1000L);
  scan.addFamily(HConstants.CATALOG_FAMILY);
  s=t.getScanner(scan);
  try {
    while (s.next() != null) {
      count+=1;
    }
    assertEquals("Number of rows should be 2",2,count);
  }
  finally {
    s.close();
  }
  count=0;
  scan=new Scan();
  scan.setTimeRange(100L,1000L);
  scan.addFamily(HConstants.CATALOG_FAMILY);
  s=t.getScanner(scan);
  try {
    while (s.next() != null) {
      count+=1;
    }
    assertEquals("Number of rows should be 2",2,count);
  }
  finally {
    s.close();
  }
  count=0;
  scan=new Scan();
  scan.setTimeStamp(100L);
  scan.addFamily(HConstants.CATALOG_FAMILY);
  s=t.getScanner(scan);
  try {
    while (s.next() != null) {
      count+=1;
    }
    assertEquals("Number of rows should be 2",2,count);
  }
  finally {
    s.close();
  }
}
