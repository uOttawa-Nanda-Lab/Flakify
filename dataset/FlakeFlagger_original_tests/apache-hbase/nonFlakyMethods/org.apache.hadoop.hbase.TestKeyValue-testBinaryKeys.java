public void testBinaryKeys() throws Exception {
  Set<KeyValue> set=new TreeSet<KeyValue>(KeyValue.COMPARATOR);
  final byte[] fam=Bytes.toBytes("col");
  final byte[] qf=Bytes.toBytes("umn");
  final byte[] nb=new byte[0];
  KeyValue[] keys={new KeyValue(Bytes.toBytes("aaaaa,\u0000\u0000,2"),fam,qf,2,nb),new KeyValue(Bytes.toBytes("aaaaa,\u0001,3"),fam,qf,3,nb),new KeyValue(Bytes.toBytes("aaaaa,,1"),fam,qf,1,nb),new KeyValue(Bytes.toBytes("aaaaa,\u1000,5"),fam,qf,5,nb),new KeyValue(Bytes.toBytes("aaaaa,a,4"),fam,qf,4,nb),new KeyValue(Bytes.toBytes("a,a,0"),fam,qf,0,nb)};
  for (int i=0; i < keys.length; i++) {
    set.add(keys[i]);
  }
  boolean assertion=false;
  int count=0;
  try {
    for (    KeyValue k : set) {
      assertTrue(count++ == k.getTimestamp());
    }
  }
 catch (  junit.framework.AssertionFailedError e) {
    assertion=true;
  }
  assertTrue(assertion);
  set=new TreeSet<KeyValue>(new KeyValue.MetaComparator());
  for (int i=0; i < keys.length; i++) {
    set.add(keys[i]);
  }
  count=0;
  for (  KeyValue k : set) {
    assertTrue(count++ == k.getTimestamp());
  }
  KeyValue[] rootKeys={new KeyValue(Bytes.toBytes(".META.,aaaaa,\u0000\u0000,0,2"),fam,qf,2,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,\u0001,0,3"),fam,qf,3,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,,0,1"),fam,qf,1,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,\u1000,0,5"),fam,qf,5,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,a,0,4"),fam,qf,4,nb),new KeyValue(Bytes.toBytes(".META.,,0"),fam,qf,0,nb)};
  set=new TreeSet<KeyValue>(new KeyValue.MetaComparator());
  for (int i=0; i < keys.length; i++) {
    set.add(rootKeys[i]);
  }
  assertion=false;
  count=0;
  try {
    for (    KeyValue k : set) {
      assertTrue(count++ == k.getTimestamp());
    }
  }
 catch (  junit.framework.AssertionFailedError e) {
    assertion=true;
  }
  set=new TreeSet<KeyValue>(new KeyValue.RootComparator());
  for (int i=0; i < keys.length; i++) {
    set.add(rootKeys[i]);
  }
  count=0;
  for (  KeyValue k : set) {
    assertTrue(count++ == k.getTimestamp());
  }
}
