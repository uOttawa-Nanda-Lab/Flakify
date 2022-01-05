public void testResultArrayEmpty() throws Exception {
  List<KeyValue> keys=new ArrayList<KeyValue>();
  Result r=new Result(keys);
  Result[] results=new Result[]{r};
  ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
  DataOutputStream out=new DataOutputStream(byteStream);
  Result.writeArray(out,results);
  results=null;
  byteStream=new ByteArrayOutputStream();
  out=new DataOutputStream(byteStream);
  Result.writeArray(out,results);
  byte[] rb=byteStream.toByteArray();
  DataInputBuffer in=new DataInputBuffer();
  in.reset(rb,0,rb.length);
  Result[] deResults=Result.readArray(in);
  assertTrue(deResults.length == 0);
  results=new Result[0];
  byteStream=new ByteArrayOutputStream();
  out=new DataOutputStream(byteStream);
  Result.writeArray(out,results);
  rb=byteStream.toByteArray();
  in=new DataInputBuffer();
  in.reset(rb,0,rb.length);
  deResults=Result.readArray(in);
  assertTrue(deResults.length == 0);
}
