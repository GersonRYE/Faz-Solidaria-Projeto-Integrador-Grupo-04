package com.fazSolidaria.fazSolidaria.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fazSolidaria.fazSolidaria.model.Endereco;

/**
 * @RestControler faz a combinação de @Controller e @ResponseBody, simplificando a criação de serviços web RESTful.
 */
@RestController 
public class ConsumirCepController implements Serializable {

  private static final long serialVersionUID = 6046704732666502085L;

  
  /**
   * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, sem a utilização de interrogação (?). 
   * O valor poderá ser acessado atraves do parametro cep que é do tipo String.
   * 
   * @GetMapping permite mapear solicitações HTTP GET, toda vez que uma solicitação GET para o endereço /getCep/{cep} foi disparada o método será executado.
   * 
   * @param cep
   * @return ResponseEntity<EnderecoTO>
   */
  @GetMapping(value="/getCep/{cep}")
  public ResponseEntity<Endereco> doObterCep(@PathVariable(name = "cep") String cep) {

    //
    /**
     * Criando e instanciando um objeto do tipo RestTemplate, este objeto possui métodos que
     * irá permitir a comunicação com o webservice
     */
    RestTemplate restTemplate = new RestTemplate();
    
    
    /**
     * Criação de uma String com nome de uri, irá armazenar a uri (endereço) a ser consumido, observe que o cep é passado como parametro e o retorno é json.
     */
    String uri = "http://viacep.com.br/ws/{cep}/json/";

    /**
     * É possível passar mais de um parametro, entretanto só iremos utilizar o cep
     * observe que foi utilizado um Map para permiter obter o valor pela chave
     */
    Map<String, String> params = new HashMap<String, String>();
    params.put("cep", cep);

    /**
     * restTemplate.getForObject(uri, CepTO.class, params);
     * 1 (URI) - Endereço do webservice que será consumido
     * 2 (EnderecoTO.class) - Classe que representa os dados do endereço e que será mapeada no retorno da requisição com base no Json.
     * 3 (Params) - Parametros que serão utilizados na requisição, os mesmos serão includos na uri. Exemplo: {cep} será substituido pelo cep informado  
     * 
     * Após a requisição ser concluida, o retorno será armazenado no enderecoTO, com todos os dados já mapeados.
     */
    Endereco enderecoModel = restTemplate.getForObject(uri, Endereco.class, params);

    /**
     * ResponseEntity permite retornar para tela os dados encontratos, o primeiro parametro recebe os dados, o segundo o status do response. 
     */
    return new ResponseEntity<Endereco>(enderecoModel, HttpStatus.OK);
  }

}