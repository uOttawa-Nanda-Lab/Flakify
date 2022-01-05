@Test public void testGetTableDescriptor() throws IOException {
  HColumnDescriptor fam1=new HColumnDescriptor("fam1");
  HColumnDescriptor fam2=new HColumnDescriptor("fam2");
  HColumnDescriptor fam3=new HColumnDescriptor("fam3");
  HTableDescriptor htd=new HTableDescriptor("myTestTable");
  htd.addFamily(fam1);
  htd.addFamily(fam2);
  htd.addFamily(fam3);
  this.admin.createTable(htd);
  HTable table=new HTable("myTestTable");
  HTableDescriptor confirmedHtd=table.getTableDescriptor();
  assertEquals(htd.compareTo(confirmedHtd),0);
}
