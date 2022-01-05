public void testMultiCellGetPutPB() throws IOException {
  String path="/" + TABLE + "/fakerow";
  CellSetModel cellSetModel=new CellSetModel();
  RowModel rowModel=new RowModel(ROW_1);
  rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_1),Bytes.toBytes(VALUE_1)));
  rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_2),Bytes.toBytes(VALUE_2)));
  cellSetModel.addRow(rowModel);
  rowModel=new RowModel(ROW_2);
  rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_1),Bytes.toBytes(VALUE_3)));
  rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_2),Bytes.toBytes(VALUE_4)));
  cellSetModel.addRow(rowModel);
  Response response=client.put(path,MIMETYPE_PROTOBUF,cellSetModel.createProtobufOutput());
  Thread.yield();
  response=client.get(path,MIMETYPE_PROTOBUF);
  assertEquals(response.getCode(),404);
  checkValuePB(TABLE,ROW_1,COLUMN_1,VALUE_1);
  checkValuePB(TABLE,ROW_1,COLUMN_2,VALUE_2);
  checkValuePB(TABLE,ROW_2,COLUMN_1,VALUE_3);
  checkValuePB(TABLE,ROW_2,COLUMN_2,VALUE_4);
  response=deleteRow(TABLE,ROW_1);
  assertEquals(response.getCode(),200);
  response=deleteRow(TABLE,ROW_2);
  assertEquals(response.getCode(),200);
}
