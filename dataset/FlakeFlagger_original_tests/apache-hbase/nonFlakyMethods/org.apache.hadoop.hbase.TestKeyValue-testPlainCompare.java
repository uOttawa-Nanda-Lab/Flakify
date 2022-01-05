public void testPlainCompare() throws Exception {
  final byte[] a=Bytes.toBytes("aaa");
  final byte[] b=Bytes.toBytes("bbb");
  final byte[] fam=Bytes.toBytes("col");
  final byte[] qf=Bytes.toBytes("umn");
  KeyValue aaa=new KeyValue(a,fam,qf,a);
  KeyValue bbb=new KeyValue(b,fam,qf,b);
  byte[] keyabb=aaa.getKey();
  byte[] keybbb=bbb.getKey();
  assertTrue(KeyValue.COMPARATOR.compare(aaa,bbb) < 0);
  assertTrue(KeyValue.KEY_COMPARATOR.compare(keyabb,0,keyabb.length,keybbb,0,keybbb.length) < 0);
  assertTrue(KeyValue.COMPARATOR.compare(bbb,aaa) > 0);
  assertTrue(KeyValue.KEY_COMPARATOR.compare(keybbb,0,keybbb.length,keyabb,0,keyabb.length) > 0);
  assertTrue(KeyValue.COMPARATOR.compare(bbb,bbb) == 0);
  assertTrue(KeyValue.KEY_COMPARATOR.compare(keybbb,0,keybbb.length,keybbb,0,keybbb.length) == 0);
  assertTrue(KeyValue.COMPARATOR.compare(aaa,aaa) == 0);
  assertTrue(KeyValue.KEY_COMPARATOR.compare(keyabb,0,keyabb.length,keyabb,0,keyabb.length) == 0);
  aaa=new KeyValue(a,fam,qf,1,a);
  bbb=new KeyValue(a,fam,qf,2,a);
  assertTrue(KeyValue.COMPARATOR.compare(aaa,bbb) > 0);
  assertTrue(KeyValue.COMPARATOR.compare(bbb,aaa) < 0);
  assertTrue(KeyValue.COMPARATOR.compare(aaa,aaa) == 0);
  aaa=new KeyValue(a,fam,qf,1,KeyValue.Type.Delete,a);
  bbb=new KeyValue(a,fam,qf,1,a);
  assertTrue(KeyValue.COMPARATOR.compare(aaa,bbb) < 0);
  assertTrue(KeyValue.COMPARATOR.compare(bbb,aaa) > 0);
  assertTrue(KeyValue.COMPARATOR.compare(aaa,aaa) == 0);
}
