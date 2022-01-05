public void testBasics() throws Exception {
  LOG.info("LOWKEY: " + KeyValue.LOWESTKEY.toString());
  check(Bytes.toBytes(getName()),Bytes.toBytes(getName()),Bytes.toBytes(getName()),1,Bytes.toBytes(getName()));
  check(Bytes.toBytes(getName()),Bytes.toBytes(getName()),null,1,null);
  check(HConstants.EMPTY_BYTE_ARRAY,Bytes.toBytes(getName()),null,1,null);
}
