package minha.app

import grails.gorm.transactions.Transactional

@Transactional
class CarroService {

    Carro get(Serializable id) {
        return Carro.get(id)
    }

    List<Carro> list(Map args) {
        return Carro.list(args)
    }

    Long count() {
        return Carro.count()
    }

    void delete(Serializable id) {
        Carro carro = Carro.get(id)
        if (carro) {
            carro.delete(flush: true)
        }
    }

    Carro save(Carro carro) {
        return carro.save(flush: true, failOnError: true)
    }


    Carro update(Serializable id, Map params) {
        Carro carro = Carro.get(id)
        
        if (!carro) {
            return null
        }
        
        if (params.cor) carro.cor = params.cor
        if (params.qtPorta) carro.qtPorta = params.qtPorta as Integer
        if (params.tpMotor) carro.tpMotor = params.tpMotor
        if (params.modelo) carro.modelo = params.modelo
        if (params.marca) carro.marca = params.marca
        
        return carro.save(flush: true, failOnError: true)
        
        }


}