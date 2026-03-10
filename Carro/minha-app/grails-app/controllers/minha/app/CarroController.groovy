package minha.app

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CarroController {

    CarroService carroService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond carroService.list(params)
    }

    def show(Long id) {
        respond carroService.get(id)
    }

    def save() {
        Carro carro = new Carro(request.JSON as Map)
        
        try {
            carroService.save(carro)
            respond carro, [status: CREATED]
        } catch (ValidationException e) {
            respond carro.errors, [status: UNPROCESSABLE_ENTITY]
        }
    }

     def update(Long id) {
        if (!id) {
            render status: BAD_REQUEST, text: "ID é obrigatório"
            return
        }

        Map dados = request.JSON as Map

        try {
            Carro carro = carroService.update(id, dados)
            
            if (!carro) {
                render status: NOT_FOUND, text: "Carro não encontrado"
                return
            }
            
            respond carro, [status: OK]
            
        } catch (ValidationException e) {
            respond e.errors, [status: UNPROCESSABLE_ENTITY]
        }
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        carroService.delete(id)
        render status: NO_CONTENT
    }
}