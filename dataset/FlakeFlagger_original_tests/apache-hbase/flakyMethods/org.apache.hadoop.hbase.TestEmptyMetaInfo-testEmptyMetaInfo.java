/** 
 * Insert some bogus rows in meta. Master should clean them up.
 * @throws IOException
 */
public void testEmptyMetaInfo() throws IOException {
  HTable t=new HTable(conf,HConstants.META_TABLE_NAME);
  final int COUNT=5;
  final byte[] tableName=Bytes.toBytes(getName());
  for (int i=0; i < COUNT; i++) {
    byte[] regionName=HRegionInfo.createRegionName(tableName,Bytes.toBytes(i == 0 ? "" : Integer.toString(i)),Long.toString(System.currentTimeMillis()));
    Put put=new Put(regionName);
    put.add(HConstants.CATALOG_FAMILY,HConstants.SERVER_QUALIFIER,Bytes.toBytes("localhost:1234"));
    t.put(put);
  }
  long sleepTime=conf.getLong("hbase.master.meta.thread.rescanfrequency",10000);
  int tries=conf.getInt("hbase.client.retries.number",5);
  int count=0;
  do {
    tries-=1;
    try {
      Thread.sleep(sleepTime);
    }
 catch (    InterruptedException e) {
    }
    Scan scan=new Scan();
    scan.addColumn(HConstants.CATALOG_FAMILY,HConstants.REGIONINFO_QUALIFIER);
    scan.addColumn(HConstants.CATALOG_FAMILY,HConstants.SERVER_QUALIFIER);
    scan.addColumn(HConstants.CATALOG_FAMILY,HConstants.STARTCODE_QUALIFIER);
    scan.addColumn(HConstants.CATALOG_FAMILY,HConstants.SPLITA_QUALIFIER);
    scan.addColumn(HConstants.CATALOG_FAMILY,HConstants.SPLITB_QUALIFIER);
    ResultScanner scanner=t.getScanner(scan);
    try {
      count=0;
      Result r;
      while ((r=scanner.next()) != null) {
        if (!r.isEmpty()) {
          count+=1;
        }
      }
    }
  finally {
      scanner.close();
    }
  }
 while (count != 0 && tries >= 0);
  assertTrue(tries >= 0);
  assertEquals(0,count);
}
