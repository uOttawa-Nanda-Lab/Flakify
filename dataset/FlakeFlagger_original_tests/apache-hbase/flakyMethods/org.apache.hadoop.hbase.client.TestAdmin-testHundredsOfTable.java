@Test public void testHundredsOfTable() throws IOException {
  final int times=100;
  HColumnDescriptor fam1=new HColumnDescriptor("fam1");
  HColumnDescriptor fam2=new HColumnDescriptor("fam2");
  HColumnDescriptor fam3=new HColumnDescriptor("fam3");
  for (int i=0; i < times; i++) {
    HTableDescriptor htd=new HTableDescriptor("table" + i);
    htd.addFamily(fam1);
    htd.addFamily(fam2);
    htd.addFamily(fam3);
    this.admin.createTable(htd);
  }
  for (int i=0; i < times; i++) {
    String tableName="table" + i;
    this.admin.disableTable(tableName);
    this.admin.enableTable(tableName);
    this.admin.disableTable(tableName);
    this.admin.deleteTable(tableName);
  }
}
