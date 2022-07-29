package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue123 extends AbstractTest {

  @Test public void spacesInVar() throws IOException{shouldCompileTo("{{var}}",$,"");shouldCompileTo("{{ var}}",$,"");shouldCompileTo("{{var }}",$,"");shouldCompileTo("{{ var }}",$,"");shouldCompileTo("{{var x }}",$,$("var",""),"");}
}
