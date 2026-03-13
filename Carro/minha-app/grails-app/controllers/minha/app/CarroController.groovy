package minha.app

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CarroController {

    CarroService carroService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static List<String> CORES_VALIDAS = [
        "branco", "preto", "prata", "cinza", "vermelho",
        "azul", "amarelo", "verde", "laranja", "marrom",
        "bege", "roxo", "rosa", "dourado", "vinho"
    ]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render carroService.list(params) as JSON
    }

    def show(Long id) {
        Carro carro = carroService.get(id)
        
        if (!carro) {
            response.status = 404
            render([erro: "Carro não encontrado"] as JSON)
            return
        }
        
        render carro as JSON
    }

    def save() {
        Carro carro = new Carro(request.JSON as Map)
        
        if (!carro.validate()) {
            response.status = 422
            render formatarErros(carro) as JSON  
            return
        }
        
        try {
            carroService.save(carro)
            render carro as JSON
        } catch (ValidationException e) {
            response.status = 422
            render formatarErros(carro) as JSON 
        }
    }

    def update(Long id) {
        if (!id) {
            response.status = 400
            render([erro: "ID é obrigatório"] as JSON)
            return
        }

        Carro carro = carroService.get(id)
        
        if (!carro) {
            response.status = 404
            render([erro: "Carro não encontrado"] as JSON)
            return
        }

        Map dados = request.JSON as Map
        carro.properties = dados
        
        if (!carro.validate()) {
            response.status = 422
            render formatarErros(carro) as JSON  
            return
        }

        try {
            carroService.save(carro)
            render carro as JSON
        } catch (ValidationException e) {
            response.status = 422
            render formatarErros(carro) as JSON  
        }
    }

    def delete(Long id) {
        if (!id) {
            response.status = 404
            render([erro: "ID é obrigatório"] as JSON)
            return
        }

        carroService.delete(id)
        render status: NO_CONTENT
    }

    private Map formatarErros(Carro carro) {
        def erros = carro.errors.allErrors.collect { error ->
            String mensagem
            
            switch(error.field) {
                case 'cor':
                if (error.code.contains('nullable') || error.code.contains('blank')) {
                    mensagem = 'Uma cor é obrigatória'
                }
                else {
                    mensagem = "Tipo de cor invalido. Marcas permitidas: (Erro: ${error.code})"
                }
                    break
                case 'qtPorta':
                mensagem = "Quantidade de portas deve ser entre 2 e 5"
                    break
                case 'tpMotor':
                mensagem = "Tipo do motor é obrigatório"
                    break
                case 'modelo':
                mensagem = "Modelo é obrigatório"
                    break
                case 'marca':
                if (error.code.contains('nullable') || error.code.contains('blank')) {
                    mensagem = "Marca é obrigatória"
            } 
                else {
                    mensagem = "Tipo de carro invalido. Marcas permitidas: (Erro: ${error.code})"
            }
                break

                default:
                    mensagem = "Campo ${error.field} inválido"
            }
            
            return [
                campo: error.field,
                valorRejeitado: error.rejectedValue,
                mensagem: mensagem
            ]
        }
        
        return [erros: erros]
    }
}