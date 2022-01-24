package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;
import org.mortbay.jetty.HttpSchemes;

public class RotaBackups {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:pedidos?delay=5s&noop=true").
                        to("");

                from("file:pedidos?delay=5s&noop=true"). //Tempo de 5s e não apagará os itens da pasta
                        routeId("rota-pedidos"). //Nomeando rotas
                        multicast(). //Espalhando a mensagem para as rotas
                        to("direct:soap").
                        to("direct:http");

                from("direct:http").
                        routeId("rota-http").
                        setProperty("pedidoId", xpath("/pedido/id/text()")).
                        setProperty("clienteId", xpath("/pedido/pagamento/email-titular/text()")).
                        split().
                        xpath("/pedido/itens/item").
                        filter().
                        xpath("/item/formato[text()='EBOOK']").
                        setProperty("ebookId", xpath("/item/livro/codigo/text()")).
                        marshal().xmljson().
                        log("${id} - ${body}").
                        setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
                        setHeader(Exchange.HTTP_QUERY, simple("ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clienteId=${property.clienteId}")).
                        to("http4://localhost:8080/webservices_war_exploded/ebook/item");

                from("direct:soap").
                        routeId("rota-soap").
                        setBody(constant("<envelope>teste</envelope>")).
                        to("mock:soap");
            }

        });

        context.start();
        Thread.sleep(20000);
        context.stop();
    }
}
