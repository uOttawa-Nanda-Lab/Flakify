@SuppressWarnings("unchecked") public void testHbaseMapWritable() throws Exception {
  HbaseMapWritable<byte[],byte[]> hmw=new HbaseMapWritable<byte[],byte[]>();
  hmw.put("key".getBytes(),"value".getBytes());
  byte[] bytes=Writables.getBytes(hmw);
  hmw=(HbaseMapWritable<byte[],byte[]>)Writables.getWritable(bytes,new HbaseMapWritable<byte[],byte[]>());
  assertTrue(hmw.size() == 1);
  assertTrue(Bytes.equals("value".getBytes(),hmw.get("key".getBytes())));
}
