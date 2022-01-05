public void testPut() throws Exception {
  byte[] row="row".getBytes();
  byte[] fam="fam".getBytes();
  byte[] qf1="qf1".getBytes();
  byte[] qf2="qf2".getBytes();
  byte[] qf3="qf3".getBytes();
  byte[] qf4="qf4".getBytes();
  byte[] qf5="qf5".getBytes();
  byte[] qf6="qf6".getBytes();
  byte[] qf7="qf7".getBytes();
  byte[] qf8="qf8".getBytes();
  long ts=System.currentTimeMillis();
  byte[] val="val".getBytes();
  Put put=new Put(row);
  put.add(fam,qf1,ts,val);
  put.add(fam,qf2,ts,val);
  put.add(fam,qf3,ts,val);
  put.add(fam,qf4,ts,val);
  put.add(fam,qf5,ts,val);
  put.add(fam,qf6,ts,val);
  put.add(fam,qf7,ts,val);
  put.add(fam,qf8,ts,val);
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
