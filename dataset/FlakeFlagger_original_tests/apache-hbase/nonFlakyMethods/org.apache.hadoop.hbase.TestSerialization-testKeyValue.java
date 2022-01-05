public void testKeyValue() throws Exception {
  byte[] row=Bytes.toBytes(getName());
  byte[] family=Bytes.toBytes(getName());
  byte[] qualifier=Bytes.toBytes(getName());
  KeyValue original=new KeyValue(row,family,qualifier);
  byte[] bytes=Writables.getBytes(original);
  KeyValue newone=(KeyValue)Writables.getWritable(bytes,new KeyValue());
  assertTrue(KeyValue.COMPARATOR.compare(original,newone) == 0);
}
