public void testBinary() throws IOException {
  MemStore mc=new MemStore(KeyValue.ROOT_COMPARATOR);
  final int start=43;
  final int end=46;
  for (int k=start; k <= end; k++) {
    byte[] kk=Bytes.toBytes(k);
    byte[] row=Bytes.toBytes(".META.,table," + Bytes.toString(kk) + ",1,"+ k);
    KeyValue key=new KeyValue(row,CONTENTS,BASIC,System.currentTimeMillis(),(CONTENTSTR + k).getBytes(HConstants.UTF8_ENCODING));
    mc.add(key);
    System.out.println(key);
  }
  int index=start;
  for (  KeyValue kv : mc.kvset) {
    System.out.println(kv);
    byte[] b=kv.getRow();
    String str=Bytes.toString(b,13,4);
    byte[] bb=Bytes.toBytes(index);
    String bbStr=Bytes.toString(bb);
    assertEquals(str,bbStr);
    index++;
  }
}
