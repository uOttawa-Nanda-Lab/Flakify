/** 
 * test for HBASE-737
 * @throws IOException
 */
@Test public void testHBase737() throws IOException {
  final byte[] FAM1=Bytes.toBytes("fam1");
  final byte[] FAM2=Bytes.toBytes("fam2");
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testHBase737"),new byte[][]{FAM1,FAM2});
  Put put=new Put(ROW);
  put.add(FAM1,Bytes.toBytes("letters"),Bytes.toBytes("abcdefg"));
  table.put(put);
  try {
    Thread.sleep(1000);
  }
 catch (  InterruptedException i) {
  }
  put=new Put(ROW);
  put.add(FAM1,Bytes.toBytes("numbers"),Bytes.toBytes("123456"));
  table.put(put);
  try {
    Thread.sleep(1000);
  }
 catch (  InterruptedException i) {
  }
  put=new Put(ROW);
  put.add(FAM2,Bytes.toBytes("letters"),Bytes.toBytes("hijklmnop"));
  table.put(put);
  long times[]=new long[3];
  Scan scan=new Scan();
  scan.addFamily(FAM1);
  scan.addFamily(FAM2);
  ResultScanner s=table.getScanner(scan);
  try {
    int index=0;
    Result r=null;
    while ((r=s.next()) != null) {
      for (      KeyValue key : r.sorted()) {
        times[index++]=key.getTimestamp();
      }
    }
  }
  finally {
    s.close();
  }
  for (int i=0; i < times.length - 1; i++) {
    for (int j=i + 1; j < times.length; j++) {
      assertTrue(times[j] > times[i]);
    }
  }
  TEST_UTIL.flush();
  for (int i=0; i < times.length; i++) {
    times[i]=0;
  }
  try {
    Thread.sleep(1000);
  }
 catch (  InterruptedException i) {
  }
  scan=new Scan();
  scan.addFamily(FAM1);
  scan.addFamily(FAM2);
  s=table.getScanner(scan);
  try {
    int index=0;
    Result r=null;
    while ((r=s.next()) != null) {
      for (      KeyValue key : r.sorted()) {
        times[index++]=key.getTimestamp();
      }
    }
  }
  finally {
    s.close();
  }
  for (int i=0; i < times.length - 1; i++) {
    for (int j=i + 1; j < times.length; j++) {
      assertTrue(times[j] > times[i]);
    }
  }
}
