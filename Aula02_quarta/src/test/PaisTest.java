package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
Pais pais, copia;
static int id = 4;
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
pais = new Pais(id, "China", 1, 1000 );
copia = new Pais(id, "China", 1, 1000 );
System.out.println(pais);
System.out.println(copia);
System.out.println(id);
}
@Test
public void test00Carregar() {
System.out.println("carregar");
//para funcionar o cliente 1 deve ter sido carregado no banco por fora
Pais fixture = new Pais(1, "Alemanha", 3, 1000 );
Pais novo = new Pais(1);
novo.carregar();
assertEquals("testa inclusao", novo, fixture);
}

@Test
public void test01Criar() {
System.out.println("criar");
pais.criar();
id = pais.getId();
System.out.println(id);
copia.setId(id);
assertEquals("testa criacao", pais, copia);
}
@Test
public void test02Atualizar() {
System.out.println("atualizar");
pais.setPopulacao(555555555);
copia.setPopulacao(555555555);
pais.atualizar();
pais.carregar();
assertEquals("testa atualizacao", pais, copia);
}

@Test
public void test03Excluir() {
System.out.println("excluir");
copia.setId(-1);
copia.setNome(null);
copia.setPopulacao(0);
copia.setArea(0);
pais.excluir();
pais.carregar();
assertEquals("testa exclusao", pais, copia);
}
}