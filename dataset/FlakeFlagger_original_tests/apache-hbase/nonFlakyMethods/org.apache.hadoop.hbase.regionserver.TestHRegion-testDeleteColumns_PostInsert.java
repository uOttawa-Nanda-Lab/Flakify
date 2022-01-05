public void testDeleteColumns_PostInsert() throws IOException, InterruptedException {
  Delete delete=new Delete(row);
  delete.deleteColumns(fam1,qual1);
  doTestDelete_AndPostInsert(delete);
}
