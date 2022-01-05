@Test public void nameConflict() throws IOException {
  Object context=$("name","an attribute","inner",$("name","an inner attribute"));
  shouldCompileTo("{{name}}",context,"an attribute");
  shouldCompileTo("{{this.name}}",context,"an attribute");
  shouldCompileTo("{{#inner}}{{name}}{{/inner}}",context,"an inner attribute");
  shouldCompileTo("{{#inner}}{{this.name}}{{/inner}}",context,"an inner attribute");
  shouldCompileTo("{{inner.name}}",context,"an inner attribute");
  Hash helpers=$("name",new Helper<Object>(){
    @Override public CharSequence apply(    final Object context,    final Options options) throws IOException {
      return "helper response";
    }
  }
);
  shouldCompileTo("{{name}}",context,helpers,"helper response");
  shouldCompileTo("{{this.name}}",context,helpers,"an attribute");
  shouldCompileTo("{{#inner}}{{name}}{{/inner}}",context,helpers,"helper response");
  shouldCompileTo("{{#inner}}{{this.name}}{{/inner}}",context,helpers,"an inner attribute");
  shouldCompileTo("{{inner.name}}",context,helpers,"an inner attribute");
}
