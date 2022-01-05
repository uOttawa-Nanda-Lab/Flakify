public void testSeek(){
  List<KeyValue> l1=new ArrayList<KeyValue>();
  l1.add(new KeyValue(row1,fam1,col5,data));
  l1.add(new KeyValue(row2,fam1,col1,data));
  l1.add(new KeyValue(row2,fam1,col2,data));
  scanners.add(new Scanner(l1));
  List<KeyValue> l2=new ArrayList<KeyValue>();
  l2.add(new KeyValue(row1,fam1,col1,data));
  l2.add(new KeyValue(row1,fam1,col2,data));
  scanners.add(new Scanner(l2));
  List<KeyValue> l3=new ArrayList<KeyValue>();
  l3.add(new KeyValue(row1,fam1,col3,data));
  l3.add(new KeyValue(row1,fam1,col4,data));
  l3.add(new KeyValue(row1,fam2,col1,data));
  l3.add(new KeyValue(row1,fam2,col2,data));
  l3.add(new KeyValue(row2,fam1,col3,data));
  scanners.add(new Scanner(l3));
  List<KeyValue> expected=new ArrayList<KeyValue>();
  expected.add(new KeyValue(row2,fam1,col1,data));
  KeyValueHeap kvh=new KeyValueHeap(scanners.toArray(new Scanner[0]),KeyValue.COMPARATOR);
  KeyValue seekKv=new KeyValue(row2,fam1,null,null);
  kvh.seek(seekKv);
  List<KeyValue> actual=new ArrayList<KeyValue>();
  actual.add(kvh.peek());
  assertEquals(expected.size(),actual.size());
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),actual.get(i));
    if (PRINT) {
      System.out.println("expected " + expected.get(i) + "\nactual   "+ actual.get(i)+ "\n");
    }
  }
}
