public void testDeleteFamily_PostInsert() throws IOException, InterruptedException {
  Delete delete=new Delete(row);
  delete.deleteFamily(fam1);
  doTestDelete_AndPostInsert(delete);
}
