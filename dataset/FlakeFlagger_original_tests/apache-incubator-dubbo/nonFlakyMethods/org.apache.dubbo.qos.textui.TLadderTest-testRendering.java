@Test public void testRendering() throws Exception {
  TLadder ladder=new TLadder();
  ladder.addItem("1");
  ladder.addItem("2");
  ladder.addItem("3");
  ladder.addItem("4");
  String result=ladder.rendering();
  String expected="1\n" + "  `-2\n" + "    `-3\n"+ "      `-4\n";
  assertThat(result,equalTo(expected));
  System.out.println(result);
}
