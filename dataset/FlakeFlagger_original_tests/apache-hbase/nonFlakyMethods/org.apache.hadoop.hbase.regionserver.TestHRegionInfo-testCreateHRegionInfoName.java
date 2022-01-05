public void testCreateHRegionInfoName() throws Exception {
  String tableName="tablename";
  final byte[] tn=Bytes.toBytes(tableName);
  String startKey="startkey";
  final byte[] sk=Bytes.toBytes(startKey);
  String id="id";
  byte[] name=HRegionInfo.createRegionName(tn,sk,id);
  String nameStr=Bytes.toString(name);
  assertEquals(nameStr,tableName + "," + startKey+ ","+ id);
}
