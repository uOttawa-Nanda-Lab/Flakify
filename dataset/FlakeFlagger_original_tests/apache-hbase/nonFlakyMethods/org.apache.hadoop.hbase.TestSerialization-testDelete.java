public void testDelete() throws Exception {
  byte[] row="row".getBytes();
  byte[] fam="fam".getBytes();
  byte[] qf1="qf1".getBytes();
  long ts=System.currentTimeMillis();
  Delete delete=new Delete(row);
  delete.deleteColumn(fam,qf1,ts);
  byte[] sb=Writables.getBytes(delete);
  Delete desDelete=(Delete)Writables.getWritable(sb,new Delete());
  assertTrue(Bytes.equals(delete.getRow(),desDelete.getRow()));
  List<KeyValue> list=null;
  List<KeyValue> desList=null;
  for (  Map.Entry<byte[],List<KeyValue>> entry : delete.getFamilyMap().entrySet()) {
    assertTrue(desDelete.getFamilyMap().containsKey(entry.getKey()));
    list=entry.getValue();
    desList=desDelete.getFamilyMap().get(entry.getKey());
    for (int i=0; i < list.size(); i++) {
      assertTrue(list.get(i).equals(desList.get(i)));
    }
  }
}
