@Test public void encoding() throws Exception {
  String encoded=AnsiOutput.toString("A",RED,BOLD,"B",NORMAL,"D",GREEN,"E",FAINT,"F");
  assertThat(encoded,equalTo("A[31;1mB[0mD[32mE[2mF[0;39m"));
}
