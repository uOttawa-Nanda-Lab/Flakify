@Test public void testListTables() throws IOException {
  byte[] t1=Bytes.toBytes("testListTables1");
  byte[] t2=Bytes.toBytes("testListTables2");
  byte[] t3=Bytes.toBytes("testListTables3");
  byte[][] tables=new byte[][]{t1,t2,t3};
  for (int i=0; i < tables.length; i++) {
    TEST_UTIL.createTable(tables[i],FAMILY);
  }
  HBaseAdmin admin=new HBaseAdmin(TEST_UTIL.getConfiguration());
  HTableDescriptor[] ts=admin.listTables();
  HashSet<HTableDescriptor> result=new HashSet<HTableDescriptor>(ts.length);
  for (int i=0; i < ts.length; i++) {
    result.add(ts[i]);
  }
  int size=result.size();
  assertTrue(size >= tables.length);
  for (int i=0; i < tables.length && i < size; i++) {
    boolean found=false;
    for (int j=0; j < ts.length; j++) {
      if (Bytes.equals(ts[j].getName(),tables[i])) {
        found=true;
        break;
      }
    }
    assertTrue("Not found: " + Bytes.toString(tables[i]),found);
  }
}
