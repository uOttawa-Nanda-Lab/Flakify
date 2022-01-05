public void testScannerLeak(){
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
  List<KeyValue> l4=new ArrayList<KeyValue>();
  scanners.add(new Scanner(l4));
  KeyValueHeap kvh=new KeyValueHeap(scanners.toArray(new Scanner[0]),KeyValue.COMPARATOR);
  while (kvh.next() != null)   ;
  for (  Scanner scanner : scanners) {
    assertTrue(scanner.isClosed());
  }
}
