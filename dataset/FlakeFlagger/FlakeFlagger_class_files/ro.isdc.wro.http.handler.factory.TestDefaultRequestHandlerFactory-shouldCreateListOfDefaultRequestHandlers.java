package ro.isdc.wro.http.handler.factory;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.http.handler.RequestHandler;


/**
 * @author Ivar Conradi Østhus
 * @created 19 May 2012
 */
public class TestDefaultRequestHandlerFactory {
  private DefaultRequestHandlerFactory victim;

  @Before
  public void setup() {
    victim = new DefaultRequestHandlerFactory();
  }

  @Test public void shouldCreateListOfDefaultRequestHandlers(){final Collection<RequestHandler> requestHandlers=victim.create();assertEquals(false,requestHandlers.isEmpty());}
}
