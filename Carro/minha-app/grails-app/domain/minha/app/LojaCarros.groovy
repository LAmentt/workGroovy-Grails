package minha.app

class LojaCarros {

    String nome
    String cnpj
    
    
    static hasMany = [carros: Carro]

    static constraints = {

        nome nullable = false, nullable: false
        cnpj blank = false, nullable: false, unique: true
    }
}
