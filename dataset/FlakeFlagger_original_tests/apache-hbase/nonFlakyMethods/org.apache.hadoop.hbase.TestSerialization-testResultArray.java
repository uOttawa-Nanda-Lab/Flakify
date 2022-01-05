public void testResultArray() throws Exception {
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
  Result result1=new Result(new KeyValue[]{kvA,kvB});
  Result result2=new Result(new KeyValue[]{kvB});
  Result result3=new Result(new KeyValue[]{kvB});
  Result[] results=new Result[]{result1,result2,result3};
  ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
  DataOutputStream out=new DataOutputStream(byteStream);
  Result.writeArray(out,results);
  byte[] rb=byteStream.toByteArray();
  DataInputBuffer in=new DataInputBuffer();
  in.reset(rb,0,rb.length);
  Result[] deResults=Result.readArray(in);
  assertTrue(results.length == deResults.length);
  for (int i=0; i < results.length; i++) {
    KeyValue[] keysA=results[i].sorted();
    KeyValue[] keysB=deResults[i].sorted();
    assertTrue(keysA.length == keysB.length);
    for (int j=0; j < keysA.length; j++) {
      assertTrue("Expected equivalent keys but found:\n" + "KeyA : " + keysA[j].toString() + "\n"+ "KeyB : "+ keysB[j].toString()+ "\n"+ keysA.length+ " total keys, "+ i+ "th so far",keysA[j].equals(keysB[j]));
    }
  }
}
