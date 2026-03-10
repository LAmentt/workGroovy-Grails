package minha.app

class Carro {

String cor
Integer qtPorta
String tpMotor
String modelo
String marca

    static constraints = {

        cor nullable: false
        qtPorta min: 2, max: 5
        tpMotor inList: ['Gasolina','Diesel','Eletrico','Hibrido']
        modelo nullable: false
        marca nullable: false
       }


       static List<String> CORES_VALIDAS=[

         "branco",
        "preto",
        "prata",
        "cinza",
        "vermelho",
        "azul",
        "azul-marinho",
        "amarelo",
        "verde",
        "laranja",
        "marrom",
        "bege",
        "roxo",
        "rosa",
        "dourado",
        "vinho"
        
        ]

        static constraints = {

            

        }

       static mapping = {

        version false
    }
}

