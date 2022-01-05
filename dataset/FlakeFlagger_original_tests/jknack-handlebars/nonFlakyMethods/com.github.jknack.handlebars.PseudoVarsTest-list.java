@Test public void list() throws IOException {
  String input="{{#list}}i={{@index}}\neven={{@even}}\nodd={{@odd}}\nfirst={{@first}}\nlast={{@last}}\n{{/list}}";
  Handlebars handlebars=new Handlebars();
  assertEquals("i=0\n" + "even=even\n" + "odd=\n"+ "first=first\n"+ "last=\n"+ "i=1\n"+ "even=\n"+ "odd=odd\n"+ "first=\n"+ "last=\n"+ "i=2\n"+ "even=even\n"+ "odd=\n"+ "first=\n"+ "last=last\n",handlebars.compile(input).apply(new Object(){
    @SuppressWarnings("unused") public List<String> getList(){
      return Arrays.asList("a","b","c");
    }
  }
));
}
