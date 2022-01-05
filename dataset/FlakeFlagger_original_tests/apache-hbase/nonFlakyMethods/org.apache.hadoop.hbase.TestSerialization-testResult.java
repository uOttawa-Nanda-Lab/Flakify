public void testResult() throws Exception {
  byte[] rowA=Bytes.toBytes("rowA");
  byte[] famA=Bytes.toBytes("famA");
  byte[] qfA=Bytes.toBytes("qfA");
  byte[] valueA=Bytes.toBytes("valueA");
  byte[] rowB=Bytes.toBytes("rowB");
  byte[] famB=Bytes.toBytes("famB");
  byte[] qfB=Bytes.toBytes("qfB");
  byte[] valueB=Bytes.toBytes("valueB");
  KeyValue kvA=new KeyValue(rowA,famA,qfA,valueA);
  KeyValue kvB=new KeyValue(rowB,famB,qfB,valueB);
  Result result=new Result(new KeyValue[]{kvA,kvB});
  byte[] rb=Writables.getBytes(result);
  Result deResult=(Result)Writables.getWritable(rb,new Result());
  assertTrue("results are not equivalent, first key mismatch",result.sorted()[0].equals(deResult.sorted()[0]));
  assertTrue("results are not equivalent, second key mismatch",result.sorted()[1].equals(deResult.sorted()[1]));
  Result r=new Result();
  byte[] b=Writables.getBytes(r);
  Result deserialized=(Result)Writables.getWritable(b,new Result());
  assertEquals(r.size(),deserialized.size());
}
