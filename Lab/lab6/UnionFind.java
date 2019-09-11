public class UnionFind {

    // TODO - Add instance variables?
    private int id[];
    private int size[];
    public int count;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        count = n;

        id = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i +=1) {
            size[i] = -1;
        }

        for (int i = 0; i < n; i +=1) {
            id[i] = i;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > count){
            throw new IllegalArgumentException("v1 is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int idv1 = id[v1];
        int idv2 = id[v2];

        if (idv1 == idv2){
            return;
        }

        if (idv1 < idv2){
            id[idv2] = idv1;
            size[idv1] += size[idv2];
        }else{
            id[idv1] = idv2;
            size[idv2] += size[idv1];
        }
        count --;



    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);

        while (vertex != id[vertex]) {
            vertex = id[vertex];
        }
        return vertex;
    }

}
