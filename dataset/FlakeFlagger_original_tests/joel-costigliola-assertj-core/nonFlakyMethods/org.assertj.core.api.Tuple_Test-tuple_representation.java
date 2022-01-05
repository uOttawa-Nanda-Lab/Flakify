@Test public void tuple_representation(){
  Tuple tuple=new Tuple("Yoda",800,"Jedi");
  assertThat(tuple.toString()).isEqualTo("(\"Yoda\", 800, \"Jedi\")");
}
