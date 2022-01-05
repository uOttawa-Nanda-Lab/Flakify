public void testKeepDeleteInmemstore(){
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf=Bytes.toBytes("testqualifier");
  byte[] val=Bytes.toBytes("testval");
  long ts=System.nanoTime();
  memstore.add(new KeyValue(row,fam,qf,ts,val));
  KeyValue delete=new KeyValue(row,fam,qf,ts,KeyValue.Type.Delete,val);
  memstore.delete(delete);
  assertEquals(1,memstore.kvset.size());
  assertEquals(delete,memstore.kvset.first());
}
