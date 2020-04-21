package Teste;

import static org.junit.Assert.assertEquals;import org.junit.Before;import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import model.Pais;
import Service.PaisService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
Pais pais, copia;
PaisService paisService;
static int id = 0;
/*
* Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
* linha com o id igual ao do escolhido para o to instanciado abaixo. Se
* houver, delete.
* Certifique-se também que sobrecarregou o equals na classe
* Cliente.
* Certifique-se que a fixture cliente1 foi criada no banco.
* Além disso, a ordem de execução dos testes é importante; por
* isso a anotação FixMethodOrder logo acima do nome desta classe
*/

@Before
public void setUp() throws Exception {
System.out.println("setup");
pais = new Pais();
pais.setId(id);
pais.setNome("China");
pais.setPopulacao(7000);
pais.setArea(90);
copia = new Pais();
copia.setId(id);
copia.setNome("China");
copia.setPopulacao(7000);
copia.setArea(90);
paisService = new PaisService();
System.out.println(pais);
System.out.println(copia);
System.out.println(id);
}
@Test
public void test00Carregar() {
System.out.println("carregar");
//para funcionar o cliente 1 deve ter sido carregado no banco por fora
Pais fixture = new Pais();
fixture.setId(4);
fixture.setNome("Brasil");
fixture.setPopulacao(70);
fixture.setArea(60);
PaisService novoService = new PaisService();
Pais novo = novoService.carregar(4);
assertEquals("testa inclusao", novo, fixture);
}

@Test
public void test01Criar() {
System.out.println("criar");
id = paisService.criar(pais);
System.out.println(id);
copia.setId(id);
assertEquals("testa criacao", pais, copia);
}
@Test
public void test02Atualizar() {
System.out.println("atualizar");
pais.setPopulacao(555555555);
copia.setPopulacao(555555555);
paisService.atualizar(pais);
pais = paisService.carregar(pais.getId());
assertEquals("testa atualizacao", pais, copia);
}

@Test
public void test03Excluir() {
System.out.println("excluir");
copia.setId(-1);
copia.setNome(null);
copia.setPopulacao(0);
copia.setArea(0);
paisService.excluir(id);
pais = paisService.carregar(id);
assertEquals("testa exclusao", pais, copia);
}
}