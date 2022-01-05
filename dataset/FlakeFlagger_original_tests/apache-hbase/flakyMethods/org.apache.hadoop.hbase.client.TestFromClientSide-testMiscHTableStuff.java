@Test public void testMiscHTableStuff() throws IOException {
  final byte[] tableAname=Bytes.toBytes("testMiscHTableStuffA");
  final byte[] tableBname=Bytes.toBytes("testMiscHTableStuffB");
  final byte[] attrName=Bytes.toBytes("TESTATTR");
  final byte[] attrValue=Bytes.toBytes("somevalue");
  byte[] value=Bytes.toBytes("value");
  HTable a=TEST_UTIL.createTable(tableAname,HConstants.CATALOG_FAMILY);
  HTable b=TEST_UTIL.createTable(tableBname,HConstants.CATALOG_FAMILY);
  Put put=new Put(ROW);
  put.add(HConstants.CATALOG_FAMILY,null,value);
  a.put(put);
  HTable newA=new HTable(TEST_UTIL.getConfiguration(),tableAname);
  Scan scan=new Scan();
  scan.addFamily(HConstants.CATALOG_FAMILY);
  ResultScanner s=newA.getScanner(scan);
  try {
    for (    Result r : s) {
      put=new Put(r.getRow());
      for (      KeyValue kv : r.sorted()) {
        put.add(kv);
      }
      b.put(put);
    }
  }
  finally {
    s.close();
  }
  HTable anotherA=new HTable(TEST_UTIL.getConfiguration(),tableAname);
  Get get=new Get(ROW);
  get.addFamily(HConstants.CATALOG_FAMILY);
  anotherA.get(get);
  HBaseAdmin admin=new HBaseAdmin(TEST_UTIL.getConfiguration());
  HTableDescriptor desc=new HTableDescriptor(a.getTableDescriptor());
  admin.disableTable(tableAname);
  desc.setValue(attrName,attrValue);
  for (  HColumnDescriptor c : desc.getFamilies())   c.setValue(attrName,attrValue);
  admin.modifyTable(tableAname,HConstants.Modify.TABLE_SET_HTD,desc);
  admin.enableTable(tableAname);
  desc=a.getTableDescriptor();
  assertTrue("wrong table descriptor returned",Bytes.compareTo(desc.getName(),tableAname) == 0);
  value=desc.getValue(attrName);
  assertFalse("missing HTD attribute value",value == null);
  assertFalse("HTD attribute value is incorrect",Bytes.compareTo(value,attrValue) != 0);
  for (  HColumnDescriptor c : desc.getFamilies()) {
    value=c.getValue(attrName);
    assertFalse("missing HCD attribute value",value == null);
    assertFalse("HCD attribute value is incorrect",Bytes.compareTo(value,attrValue) != 0);
  }
}
