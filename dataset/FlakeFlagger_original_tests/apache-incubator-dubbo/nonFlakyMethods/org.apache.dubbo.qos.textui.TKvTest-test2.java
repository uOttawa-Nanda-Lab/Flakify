@Test public void test2() throws Exception {
  TKv tKv=new TKv();
  tKv.add("KEY-1","ABCDEFGHIJKLMNOPQRSTUVWXYZ");
  tKv.add("KEY-2","1234567890");
  tKv.add("KEY-3","1234567890");
  String kv=tKv.rendering();
  assertThat(kv,containsString("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
  System.out.println(kv);
}
