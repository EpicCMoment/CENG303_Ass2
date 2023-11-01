import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Download_CVEs {


    static class Proxy {
        public String hostname;
        public int port;

        Proxy(String hostname, int port) {
            this.hostname = hostname;
            this.port = port;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // how many times to send request
        int request_amount = 25;

        // how many CVEs to get from a single request
        int request_size = 2_000;

        // read available proxies from the file
        ArrayList<Proxy> proxies = readProxiesFromFile(args[1]);

        // create a new request handler client
        HttpClient client = getClientWithNewProxy(proxies);


        PrintWriter out = null;
        try {
            // create a buffered stream to the output file
            out = new PrintWriter(new FileOutputStream("vulns.json"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        // send the requests synchronously
        for (int i = 0; i < request_amount * request_size; i += request_size) {

            Thread.sleep(1_000);

            if (i % 3 == 0) {
                client = getClientWithNewProxy(proxies);
            }

            // build the request URL on-fly
            String request_url = String.format(
                    "https://services.nvd.nist.gov/rest/json/cves/2.0/?resultsPerPage=%d&startIndex=%d",
                    request_size,
                    i
            );


            // build the HTTP GET package
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(request_url))
                    .build();

            String json_data = "";

            try  {

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                System.err.println(response.statusCode());

                if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                    System.err.println("Changing the proxy!!");;
                    client = getClientWithNewProxy(proxies);
                    i -= request_size;
                    continue;
                }

                // send the request and capture the response's body (JSON data) into a string
                json_data = client.send(request, HttpResponse.BodyHandlers.ofString()).body();


            } catch(Exception e) {

                System.out.println(e.getMessage());
                System.exit(-1);

            }

            out.print(json_data);


        }

        // close the output file stream
        out.close();

        // close the connection handler
        client.close();

    }

    private static ArrayList<Proxy> readProxiesFromFile(String fileName) {

        ArrayList<Proxy> proxies = new ArrayList<>();

        Scanner fileReader = null;

        try {
            fileReader = new Scanner(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        while (fileReader.hasNext()) {

            String address = fileReader.nextLine();

            String[] parts = address.split(":");

            proxies.add(new Proxy(parts[0], Integer.parseInt(parts[1])));

        }

        fileReader.close();

        return proxies;


    }


    private static HttpClient getClientWithNewProxy(ArrayList<Proxy> proxies) {

        Proxy newProxy = proxies.getFirst();

        // parse proxy information
        String hostname = newProxy.hostname;
        int port = newProxy.port;

        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress(hostname, port)))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();


        // remove the used proxy from the beginning of the array
        proxies.removeFirst();

        // add the used proxy to the end
        proxies.addLast(newProxy);

        // this way we cycle through all the proxies without exhaustion

        return client;
    }
}
