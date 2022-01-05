@SuppressWarnings("boxing") public void testReadObjectDataInputConfiguration() throws IOException {
  HBaseConfiguration conf=new HBaseConfiguration();
  final int COUNT=101;
  assertTrue(doType(conf,COUNT,int.class).equals(COUNT));
  final byte[] testing="testing".getBytes();
  byte[] result=(byte[])doType(conf,testing,testing.getClass());
  assertTrue(WritableComparator.compareBytes(testing,0,testing.length,result,0,result.length) == 0);
  boolean exception=false;
  try {
    doType(conf,new File("a"),File.class);
  }
 catch (  UnsupportedOperationException uoe) {
    exception=true;
  }
  assertTrue(exception);
  final byte A='A';
  byte[] bytes=new byte[1];
  bytes[0]=A;
  Object obj=doType(conf,bytes,byte[].class);
  assertTrue(((byte[])obj)[0] == A);
  obj=doType(conf,new Text(""),Text.class);
  assertTrue(obj instanceof Text);
  List<String> list=new ArrayList<String>();
  list.add("hello");
  list.add("world");
  list.add("universe");
  obj=doType(conf,list,List.class);
  assertTrue(obj instanceof List);
  Assert.assertArrayEquals(list.toArray(),((List)obj).toArray());
  ArrayList<String> arr=new ArrayList<String>();
  arr.add("hello");
  arr.add("world");
  arr.add("universe");
  obj=doType(conf,arr,ArrayList.class);
  assertTrue(obj instanceof ArrayList);
  Assert.assertArrayEquals(list.toArray(),((ArrayList)obj).toArray());
  obj=doType(conf,new PrefixFilter(HConstants.EMPTY_BYTE_ARRAY),PrefixFilter.class);
  assertTrue(obj instanceof PrefixFilter);
}
