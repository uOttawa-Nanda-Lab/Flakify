public void testPutSameKey(){
  byte[] bytes=Bytes.toBytes(getName());
  KeyValue kv=new KeyValue(bytes,bytes,bytes,bytes);
  this.memstore.add(kv);
  byte[] other=Bytes.toBytes("somethingelse");
  KeyValue samekey=new KeyValue(bytes,bytes,bytes,other);
  this.memstore.add(samekey);
  KeyValue found=this.memstore.kvset.first();
  assertEquals(1,this.memstore.kvset.size());
  assertTrue(Bytes.toString(found.getValue()),Bytes.equals(samekey.getValue(),found.getValue()));
}
