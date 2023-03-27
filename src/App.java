import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main(String[] args) throws Exception 
    {
		// Protocolo: conexão HTTPS e buscar 250 filmes
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		String body = response.body();
		System.out.println(body);
	
		// Extrair/Parciar dados desejados (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> lista_filmes = parser.parse(body);
		//System.out.println(lista_filmes.size());
		
		// Exibir e manipular os dados
		for (Map<String,String> filme : lista_filmes)
		{
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));
			System.out.println();
		}
    }
}
