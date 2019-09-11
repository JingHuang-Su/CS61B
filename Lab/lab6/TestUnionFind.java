import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUnionFind {
    @Test
    public void  UnionFindSizeOfTest() {
        UnionFind uf = new UnionFind(10);
        int ExpectSize = -1;
        int ActuralSize = uf.sizeOf(4);
        assertEquals(ExpectSize, ActuralSize);
    }

    @Test
    public void UnionTest(){
        UnionFind uf = new UnionFind(10);
        //union 1, 6
        uf.union(1, 6);
        int ExpectSize1 = -2;
        int ActuralSize1 = uf.sizeOf(1);
        assertEquals(ExpectSize1, ActuralSize1);

        int ExpectId1 = 1;
        int ActuralIdforsix = uf.find(6);
        assertEquals(ExpectId1, ActuralIdforsix);

        int ExpectCount = 9;
        int Actural = uf.count;
        assertEquals(ExpectCount, Actural);
        //union 1, 7
        uf.union(1, 7);
        int ExpectSize2 = -3;
        int ActuralSize2 = uf.sizeOf(1);
        assertEquals(ExpectSize2, ActuralSize2);

        int ExpectId2 = 1;
        int ActuralIdforseven = uf.find(7);
        assertEquals(ExpectId2, ActuralIdforseven);

        int ExpectCount2 = 8;
        int Actural2 = uf.count;
        assertEquals(ExpectCount2, Actural2);
        //union 7, 2
        uf.union(7, 2);
        int ExpectSize3 = -4;
        int ActuralSize3 = uf.sizeOf(1);
        assertEquals(ExpectSize3, ActuralSize3);

        int ExpectId3 = 1;
        int ActuralIdfortwo = uf.find(2);
        assertEquals(ExpectId3, ActuralIdfortwo);

        int ExpectCount3 = 7;
        int Actural3 = uf.count;
        assertEquals(ExpectCount3, Actural3);

    }

    @Test
    public void UnionConnect(){
        UnionFind uf = new UnionFind(10);
        uf.union(1, 6);
        boolean Actural = uf.connected(1, 6);
        assertTrue(Actural);
    }

}
