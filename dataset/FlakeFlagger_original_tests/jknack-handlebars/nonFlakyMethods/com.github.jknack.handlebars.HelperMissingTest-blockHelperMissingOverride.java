@Test public void blockHelperMissingOverride() throws IOException {
  Hash helpers=$(Handlebars.HELPER_MISSING,new Helper<Object>(){
    @Override public CharSequence apply(    final Object context,    final Options options) throws IOException {
      return options.fn.text();
    }
  }
);
  shouldCompileTo("{{#missing x}}Raw display{{/missing}}",new Object(),helpers,"Raw display");
}
