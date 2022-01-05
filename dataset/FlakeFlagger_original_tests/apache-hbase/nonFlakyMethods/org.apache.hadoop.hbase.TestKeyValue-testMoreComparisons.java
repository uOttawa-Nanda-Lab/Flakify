public void testMoreComparisons() throws Exception {
  long now=System.currentTimeMillis();
  KeyValue a=new KeyValue(Bytes.toBytes(".META.,,99999999999999"),now);
  KeyValue b=new KeyValue(Bytes.toBytes(".META.,,1"),now);
  KVComparator c=new KeyValue.RootComparator();
  assertTrue(c.compare(b,a) < 0);
  KeyValue aa=new KeyValue(Bytes.toBytes(".META.,,1"),now);
  KeyValue bb=new KeyValue(Bytes.toBytes(".META.,,1"),Bytes.toBytes("info"),Bytes.toBytes("regioninfo"),1235943454602L,(byte[])null);
  assertTrue(c.compare(aa,bb) < 0);
  KeyValue aaa=new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236020145502"),now);
  KeyValue bbb=new KeyValue(Bytes.toBytes("TestScanMultipleVersions,,99999999999999"),now);
  c=new KeyValue.MetaComparator();
  assertTrue(c.compare(bbb,aaa) < 0);
  KeyValue aaaa=new KeyValue(Bytes.toBytes("TestScanMultipleVersions,,1236023996656"),Bytes.toBytes("info"),Bytes.toBytes("regioninfo"),1236024396271L,(byte[])null);
  assertTrue(c.compare(aaaa,bbb) < 0);
  KeyValue x=new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236034574162"),Bytes.toBytes("info"),Bytes.toBytes(""),9223372036854775807L,(byte[])null);
  KeyValue y=new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236034574162"),Bytes.toBytes("info"),Bytes.toBytes("regioninfo"),1236034574912L,(byte[])null);
  assertTrue(c.compare(x,y) < 0);
  comparisons(new KeyValue.MetaComparator());
  comparisons(new KeyValue.KVComparator());
  metacomparisons(new KeyValue.RootComparator());
  metacomparisons(new KeyValue.MetaComparator());
}
