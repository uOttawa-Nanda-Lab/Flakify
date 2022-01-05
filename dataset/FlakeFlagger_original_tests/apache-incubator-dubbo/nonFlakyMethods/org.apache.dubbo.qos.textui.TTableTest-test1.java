@Test public void test1() throws Exception {
  TTable table=new TTable(4);
  table.addRow(1,"one","uno","un");
  table.addRow(2,"two","dos","deux");
  String result=table.rendering();
  String expected="+-+---+---+----+\n" + "|1|one|uno|un  |\n" + "+-+---+---+----+\n"+ "|2|two|dos|deux|\n"+ "+-+---+---+----+\n";
  assertThat(result,equalTo(expected));
  System.out.println(result);
}
