@Test public void test1(){
  TKv tKv=new TKv(new TTable.ColumnDefine(TTable.Align.RIGHT),new TTable.ColumnDefine(10,false,TTable.Align.LEFT));
  tKv.add("KEY-1","ABCDEFGHIJKLMNOPQRSTUVWXYZ");
  tKv.add("KEY-2","1234567890");
  tKv.add("KEY-3","1234567890");
  TTable tTable=new TTable(new TTable.ColumnDefine[]{new TTable.ColumnDefine(),new TTable.ColumnDefine(20,false,TTable.Align.LEFT)});
  String kv=tKv.rendering();
  assertThat(kv,containsString("ABCDEFGHIJ\n"));
  assertThat(kv,containsString("KLMNOPQRST\n"));
  assertThat(kv,containsString("UVWXYZ\n"));
  tTable.addRow("OPTIONS",kv);
  String table=tTable.rendering();
  assertThat(table,containsString("|OPTIONS|"));
  assertThat(table,containsString("|KEY-3"));
  System.out.println(table);
}
