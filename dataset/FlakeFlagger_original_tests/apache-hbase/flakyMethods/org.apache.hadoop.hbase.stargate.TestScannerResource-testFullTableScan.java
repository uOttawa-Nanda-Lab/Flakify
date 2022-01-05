public void testFullTableScan() throws IOException {
  ScannerModel model=new ScannerModel();
  model.addColumn(Bytes.toBytes(COLUMN_1));
  assertEquals(fullTableScan(model),expectedRows1);
  model=new ScannerModel();
  model.addColumn(Bytes.toBytes(COLUMN_2));
  assertEquals(fullTableScan(model),expectedRows2);
}
