public void testResultEmpty() throws Exception {
  List<KeyValue> keys=new ArrayList<KeyValue>();
  Result r=new Result(keys);
  assertTrue(r.isEmpty());
  byte[] rb=Writables.getBytes(r);
  Result deserializedR=(Result)Writables.getWritable(rb,new Result());
  assertTrue(deserializedR.isEmpty());
}
