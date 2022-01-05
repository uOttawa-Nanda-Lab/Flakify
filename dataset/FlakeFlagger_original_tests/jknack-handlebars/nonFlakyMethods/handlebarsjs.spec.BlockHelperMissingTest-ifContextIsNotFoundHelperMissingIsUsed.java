@Test public void ifContextIsNotFoundHelperMissingIsUsed() throws IOException {
  String string="{{hello}} {{link_to world}}";
  String context="{ hello: Hello, world: world }";
  Hash helpers=$(Handlebars.HELPER_MISSING,new Helper<String>(){
    @Override public CharSequence apply(    final String context,    final Options options) throws IOException {
      return new Handlebars.SafeString("<a>" + context + "</a>");
    }
  }
);
  shouldCompileTo(string,context,helpers,"Hello <a>world</a>");
}
