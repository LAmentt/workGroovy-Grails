package minha.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CarroServiceSpec extends Specification {

    CarroService carroService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Carro(...).save(flush: true, failOnError: true)
        //new Carro(...).save(flush: true, failOnError: true)
        //Carro carro = new Carro(...).save(flush: true, failOnError: true)
        //new Carro(...).save(flush: true, failOnError: true)
        //new Carro(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //carro.id
    }

    void "test get"() {
        setupData()

        expect:
        carroService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Carro> carroList = carroService.list(max: 2, offset: 2)

        then:
        carroList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        carroService.count() == 5
    }

    void "test delete"() {
        Long carroId = setupData()

        expect:
        carroService.count() == 5

        when:
        carroService.delete(carroId)
        sessionFactory.currentSession.flush()

        then:
        carroService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Carro carro = new Carro()
        carroService.save(carro)

        then:
        carro.id != null
    }
}
