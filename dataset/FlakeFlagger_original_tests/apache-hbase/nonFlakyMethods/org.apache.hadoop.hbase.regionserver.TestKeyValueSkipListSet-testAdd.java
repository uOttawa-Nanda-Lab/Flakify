public void testAdd() throws Exception {
  byte[] bytes=Bytes.toBytes(getName());
  KeyValue kv=new KeyValue(bytes,bytes,bytes,bytes);
  this.kvsls.add(kv);
  assertTrue(this.kvsls.contains(kv));
  assertEquals(1,this.kvsls.size());
  KeyValue first=this.kvsls.first();
  assertTrue(kv.equals(first));
  assertTrue(Bytes.equals(kv.getValue(),first.getValue()));
  byte[] overwriteValue=Bytes.toBytes("overwrite");
  KeyValue overwrite=new KeyValue(bytes,bytes,bytes,overwriteValue);
  this.kvsls.add(overwrite);
  assertEquals(1,this.kvsls.size());
  first=this.kvsls.first();
  assertTrue(Bytes.equals(overwrite.getValue(),first.getValue()));
  assertFalse(Bytes.equals(overwrite.getValue(),kv.getValue()));
}
