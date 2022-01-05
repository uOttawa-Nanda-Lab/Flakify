@Test public void falsy() throws IOException {
  shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",""),"false");
  shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",""),"false");
  shouldCompileTo("{{^value}}false{{/value}}",$("value",""),"false");
  shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",null),"false");
  shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",null),"false");
  shouldCompileTo("{{^value}}false{{/value}}",$("value",null),"false");
  shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",false),"false");
  shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",false),"false");
  shouldCompileTo("{{^value}}false{{/value}}",$("value",false),"false");
  shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",Collections.emptyList()),"false");
  shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",Collections.emptyList()),"false");
  shouldCompileTo("{{^value}}false{{/value}}",$("value",Collections.emptyList()),"false");
}
