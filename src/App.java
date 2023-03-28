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
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
		URI endereco = URI.create(url);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		String body = response.body();
	
		// Extrair/Parciar dados desejados (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> lista_filmes = parser.parse(body);
		//System.out.println(lista_filmes.size());
		
		// Exibir e manipular os dados
		System.out.println("\u001b[37m");
		for (Map<String,String> filme : lista_filmes)
		{
			System.out.print("\u001b[33m"+ filme.get("title")+ "\u001b[m");

			String n_stars = filme.get("imDbRating");
			char a = n_stars[0];


			System.out.println("\u001b[34m" + filme.get("image") + "\u001b[m");
			System.out.println();
		}
    }
}
