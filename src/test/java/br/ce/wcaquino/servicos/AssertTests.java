package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTests {

    @Test
    public void test() {
        int val1 = 1;
        int val2 = 1;

        // Trabalhando com Booleans
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals(val1, val2);

        // Delta -> margem de erro para valores decimais (Double, Float, etc)
        Assert.assertEquals(0.512, 0.51234, 0.001);
        Assert.assertEquals(3.14, Math.PI, 0.01);

        // Autobox e Unboxing
        // Varia entre o Tipo Primitivo e o Objeto
        int i = 5;
        Integer i2 = 5;
        Assert.assertTrue(i == i2);

        // Assert.assertEquals(i, i2); -> Não Pode!
        Assert.assertEquals(i, i2.intValue());

        // Trabalhando com Strings
        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "Bola");

        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        // Trabalhando com Instâncias
        Usuario user1 = new Usuario();
        Usuario user2 = new Usuario();
        Usuario user3 = user2; // aponta para a mesma intância

        // As Instâncias são diferentes!
        Assert.assertNotSame(user1, user2);
        Assert.assertSame(user2, user3);

        //Usa o equals() da própria classe (Usuario) para comparar os objetos
        Assert.assertEquals(user1, user2);
    }
}
