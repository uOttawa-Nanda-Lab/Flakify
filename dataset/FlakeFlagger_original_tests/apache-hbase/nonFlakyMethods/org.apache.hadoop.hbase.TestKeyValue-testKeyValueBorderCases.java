/** 
 * Tests cases where rows keys have characters below the ','. See HBASE-832
 * @throws IOException
 */
public void testKeyValueBorderCases() throws IOException {
  KeyValue rowA=new KeyValue(Bytes.toBytes("testtable,www.hbase.org/,1234"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  KeyValue rowB=new KeyValue(Bytes.toBytes("testtable,www.hbase.org/%20,99999"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  assertTrue(KeyValue.META_COMPARATOR.compare(rowA,rowB) < 0);
  rowA=new KeyValue(Bytes.toBytes("testtable,,1234"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  rowB=new KeyValue(Bytes.toBytes("testtable,$www.hbase.org/,99999"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  assertTrue(KeyValue.META_COMPARATOR.compare(rowA,rowB) < 0);
  rowA=new KeyValue(Bytes.toBytes(".META.,testtable,www.hbase.org/,1234,4321"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  rowB=new KeyValue(Bytes.toBytes(".META.,testtable,www.hbase.org/%20,99999,99999"),Bytes.toBytes("fam"),Bytes.toBytes(""),Long.MAX_VALUE,(byte[])null);
  assertTrue(KeyValue.ROOT_COMPARATOR.compare(rowA,rowB) < 0);
}
