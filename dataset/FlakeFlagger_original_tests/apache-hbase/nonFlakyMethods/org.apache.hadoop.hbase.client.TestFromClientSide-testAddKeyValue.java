@Test public void testAddKeyValue() throws IOException {
  final byte[] CONTENTS_FAMILY=Bytes.toBytes("contents");
  final byte[] value=Bytes.toBytes("abcd");
  final byte[] row1=Bytes.toBytes("row1");
  final byte[] row2=Bytes.toBytes("row2");
  byte[] qualifier=Bytes.toBytes("qf1");
  Put put=new Put(row1);
  KeyValue kv=new KeyValue(row1,CONTENTS_FAMILY,qualifier,value);
  boolean ok=true;
  try {
    put.add(kv);
  }
 catch (  IOException e) {
    ok=false;
  }
  assertEquals(true,ok);
  kv=new KeyValue(row2,CONTENTS_FAMILY,qualifier,value);
  ok=false;
  try {
    put.add(kv);
  }
 catch (  IOException e) {
    ok=true;
  }
  assertEquals(true,ok);
}
