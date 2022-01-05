@Test public void test2() throws Exception {
  TTable table=new TTable(new TTable.ColumnDefine[]{new TTable.ColumnDefine(5,true,TTable.Align.LEFT),new TTable.ColumnDefine(10,false,TTable.Align.MIDDLE),new TTable.ColumnDefine(10,false,TTable.Align.RIGHT)});
  table.addRow(1,"abcde","ABCDE");
  String result=table.rendering();
  String expected="+-+----------+----------+\n" + "|1|   abcde  |     ABCDE|\n" + "+-+----------+----------+\n";
  assertThat(result,equalTo(expected));
  System.out.println(result);
}
