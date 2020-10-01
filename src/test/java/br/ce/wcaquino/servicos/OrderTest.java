package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {

    private static int num; // DEVE SER STATIC

    @Test
    public void t1_inicia() {
        num = 1;
    }

    @Test
    public void t2_verifica() {
        Assert.assertEquals(1, num);
    }
}
