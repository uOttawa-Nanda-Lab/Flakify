@Test public void useMissingValue() throws IOException {
  final Object hash=new Object();
  Handlebars handlebars=new Handlebars().with(new MissingValueResolver(){
    @Override public String resolve(    final Object context,    final String var){
      assertEquals(hash,context);
      assertEquals("missingVar",var);
      return "(none)";
    }
  }
);
  assertEquals("(none)",handlebars.compile("{{missingVar}}").apply(hash));
}
