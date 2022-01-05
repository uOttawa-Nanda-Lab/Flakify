public void testRetainsDeleteVersion() throws IOException {
  memstore.add(KeyValueTestUtil.create("row1","fam","a",100,"dont-care"));
  KeyValue delete=KeyValueTestUtil.create("row1","fam","a",100,KeyValue.Type.Delete,"dont-care");
  memstore.delete(delete);
  assertEquals(1,memstore.kvset.size());
  assertEquals(delete,memstore.kvset.first());
}
