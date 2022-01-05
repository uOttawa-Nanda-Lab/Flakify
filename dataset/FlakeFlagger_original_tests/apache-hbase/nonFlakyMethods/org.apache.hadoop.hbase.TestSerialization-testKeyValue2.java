public void testKeyValue2() throws Exception {
  byte[] row=getName().getBytes();
  byte[] fam="fam".getBytes();
  byte[] qf="qf".getBytes();
  long ts=System.currentTimeMillis();
  byte[] val="val".getBytes();
  KeyValue kv=new KeyValue(row,fam,qf,ts,val);
  byte[] mb=Writables.getBytes(kv);
  KeyValue deserializedKv=(KeyValue)Writables.getWritable(mb,new KeyValue());
  assertTrue(Bytes.equals(kv.getBuffer(),deserializedKv.getBuffer()));
  assertEquals(kv.getOffset(),deserializedKv.getOffset());
  assertEquals(kv.getLength(),deserializedKv.getLength());
}
