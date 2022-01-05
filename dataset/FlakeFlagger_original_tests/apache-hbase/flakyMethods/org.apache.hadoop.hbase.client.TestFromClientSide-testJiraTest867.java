/** 
 * HBASE-867 If millions of columns in a column family, hbase scanner won't come up Test will create numRows rows, each with numColsPerRow columns (1 version each), and attempt to scan them all. To test at scale, up numColsPerRow to the millions (have not gotten that to work running as junit though)
 */
@Test public void testJiraTest867() throws Exception {
  int numRows=10;
  int numColsPerRow=2000;
  byte[] TABLE=Bytes.toBytes("testJiraTest867");
  byte[][] ROWS=makeN(ROW,numRows);
  byte[][] QUALIFIERS=makeN(QUALIFIER,numColsPerRow);
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY);
  for (int i=0; i < numRows; i++) {
    Put put=new Put(ROWS[i]);
    for (int j=0; j < numColsPerRow; j++) {
      put.add(FAMILY,QUALIFIERS[j],QUALIFIERS[j]);
    }
    assertTrue("Put expected to contain " + numColsPerRow + " columns but "+ "only contains "+ put.size(),put.size() == numColsPerRow);
    ht.put(put);
  }
  Get get=new Get(ROWS[numRows - 1]);
  Result result=ht.get(get);
  assertNumKeys(result,numColsPerRow);
  KeyValue[] keys=result.sorted();
  for (int i=0; i < result.size(); i++) {
    assertKey(keys[i],ROWS[numRows - 1],FAMILY,QUALIFIERS[i],QUALIFIERS[i]);
  }
  Scan scan=new Scan();
  ResultScanner scanner=ht.getScanner(scan);
  int rowCount=0;
  while ((result=scanner.next()) != null) {
    assertNumKeys(result,numColsPerRow);
    KeyValue[] kvs=result.sorted();
    for (int i=0; i < numColsPerRow; i++) {
      assertKey(kvs[i],ROWS[rowCount],FAMILY,QUALIFIERS[i],QUALIFIERS[i]);
    }
    rowCount++;
  }
  scanner.close();
  assertTrue("Expected to scan " + numRows + " rows but actually scanned "+ rowCount+ " rows",rowCount == numRows);
  TEST_UTIL.flush();
  get=new Get(ROWS[numRows - 1]);
  result=ht.get(get);
  assertNumKeys(result,numColsPerRow);
  keys=result.sorted();
  for (int i=0; i < result.size(); i++) {
    assertKey(keys[i],ROWS[numRows - 1],FAMILY,QUALIFIERS[i],QUALIFIERS[i]);
  }
  scan=new Scan();
  scanner=ht.getScanner(scan);
  rowCount=0;
  while ((result=scanner.next()) != null) {
    assertNumKeys(result,numColsPerRow);
    KeyValue[] kvs=result.sorted();
    for (int i=0; i < numColsPerRow; i++) {
      assertKey(kvs[i],ROWS[rowCount],FAMILY,QUALIFIERS[i],QUALIFIERS[i]);
    }
    rowCount++;
  }
  scanner.close();
  assertTrue("Expected to scan " + numRows + " rows but actually scanned "+ rowCount+ " rows",rowCount == numRows);
}
