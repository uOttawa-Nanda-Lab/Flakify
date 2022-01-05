@Test @SuppressWarnings("unchecked") public void testParse2Class() throws Exception {
  int[] o1={1,2,3,4,5}, o2=JSON.parse("[1.2,2,3,4,5]",int[].class);
  assertEquals(o2.length,5);
  for (int i=0; i < 5; i++)   assertEquals(o1[i],o2[i]);
  List l1=(List)JSON.parse("[1.2,2,3,4,5]",List.class);
  assertEquals(l1.size(),5);
  for (int i=0; i < 5; i++)   assertEquals(o1[i],((Number)l1.get(i)).intValue());
  Bean bean=JSON.parse("{name:'qianlei',array:[1,2,3,4,98.123],displayName:'钱磊',$$:214726,$b:TRUE}",Bean.class);
  assertEquals(bean.getName(),"qianlei");
  assertEquals(bean.getDisplayName(),"钱磊");
  assertEquals(bean.array.length,5);
  assertEquals(bean.$$,214726);
  assertEquals(bean.$b,true);
  for (int i=0; i < 10000; i++)   JSON.parse("{name:'qianlei',array:[1,2,3,4,98.123],displayName:'钱磊'}",Bean1.class);
  long now=System.currentTimeMillis();
  for (int i=0; i < 10000; i++)   JSON.parse("{name:'qianlei',array:[1,2,3,4,98.123],displayName:'钱磊'}",Bean1.class);
  System.out.println("parse to Class 10000 times in: " + (System.currentTimeMillis() - now));
}
