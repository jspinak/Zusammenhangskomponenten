//*******************************************************
//
// Wir finden Zusammenhangskomponente beim Durchlaufen eines ungerichteten Graphen
// mit einer Tiefensuche, genau wie den Pseudocode in Vorlesungsfolie 7
// 
//*******************************************************

class zusammenhang {
    private int DFS_Zahler = 1;
    private int[] S;
    private int[] besucht;
    private int[] DFS;
    private int[] P;
    private int[][] m = new int[13][5];
    private int[][] im_stack = new int[13][13];

    public zusammenhang() {
        int l;
        l = m.length;
        S = new int[l];
        besucht = new int[l];
        DFS = new int[l];
        P = new int[l];
    }

    public void zh_finden() {
        int i,j,l;
        l = m.length; //12
        for (i=1; i<l; i++) { //1-11
            for (j=0; j<m[i].length; j++) {
                if (besucht[i] == 0) {
                    ZSuche(i,i);
                }
            }
        }
    }

    public void ZSuche(int u,int u_) {
        int i,v;
        besucht[u] = 1;
        DFS[u] = DFS_Zahler;
        P[u] = DFS_Zahler;                            
        DFS_Zahler++;
        System.out.println("------------------------- u|u' "+u+"|"+u_);
        for (i=0; i<m[u].length; i++) {
            v = m[u][i];
            System.out.println("----------------- v="+v);
            if (im_stack[u][v] == 0 && im_stack[v][u] == 0) {
                System.out.println("stack+ "+u+"-"+v); //u = Knote, m[u][i] = v (andere Knote)
                im_stack[u][v] = 1;
            } 
            if (besucht[v] == 0) {
                ZSuche(v,u);
                System.out.println("zuruck zu ---------------------------- u|u'|v "+u+"|"+u_+"|"+v);
                if (P[v] >= DFS[u]) {
                    System.out.println("P[v](="+P[v]+") >= P[u](="+P[u]+")");
                    System.out.println("nimm alle Kanten bis einschliesslich {"+u+","+v+"} vom Stack und gebe sie als eine Komponente aus");
                }
                P[u] = Math.min(P[u],P[v]);
                System.out.println("P[u] ist min von P[u](="+P[u]+"),P[v](="+P[v]+")");
            } else {
                if (v != u_) {
                    System.out.println(v+" schon besucht & v(="+v+") != u'(="+u_+")");
                    System.out.println("P[u] = min von P[u](="+P[u]+"),DFS[v](="+DFS[v]+")");
                    P[u] = Math.min(P[u],DFS[v]);    
                }
            }
        }
    }

    public static void main(String[] args) {
        zusammenhang zs = new zusammenhang();
        zs.m[1] = new int[] {2,4};
        zs.m[2] = new int[] {1,3,4};
        zs.m[3] = new int[] {2,4,5,6};
        zs.m[4] = new int[] {1,2,3,5,12};
        zs.m[5] = new int[] {3,4,6,12};
        zs.m[6] = new int[] {3,5,7,11};
        zs.m[7] = new int[] {6,8,11};
        zs.m[8] = new int[] {7,9,10,11};
        zs.m[9] = new int[] {8,10};
        zs.m[10] = new int[] {8,9};
        zs.m[11] = new int[] {6,7,8};
        zs.m[12] = new int[] {4,5};
        zs.zh_finden();
    }
}
