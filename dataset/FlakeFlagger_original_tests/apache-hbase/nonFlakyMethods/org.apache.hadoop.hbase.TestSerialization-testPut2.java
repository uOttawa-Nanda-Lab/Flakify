public void testPut2() throws Exception {
  byte[] row="testAbort,,1243116656250".getBytes();
  byte[] fam="historian".getBytes();
  byte[] qf1="creation".getBytes();
  long ts=9223372036854775807L;
  byte[] val="dont-care".getBytes();
  Put put=new Put(row);
  put.add(fam,qf1,ts,val);
  byte[] sb=Writables.getBytes(put);
  Put desPut=(Put)Writables.getWritable(sb,new Put());
  assertTrue(Bytes.equals(put.getRow(),desPut.getRow()));
  List<KeyValue> list=null;
  List<KeyValue> desList=null;
  for (  Map.Entry<byte[],List<KeyValue>> entry : put.getFamilyMap().entrySet()) {
    assertTrue(desPut.getFamilyMap().containsKey(entry.getKey()));
    list=entry.getValue();
    desList=desPut.getFamilyMap().get(entry.getKey());
    for (int i=0; i < list.size(); i++) {
      assertTrue(list.get(i).equals(desList.get(i)));
    }
  }
}
