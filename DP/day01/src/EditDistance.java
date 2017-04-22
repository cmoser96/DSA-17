import java.lang.Math;
public class EditDistance {

    public static int minEditDist(String a, String b) {
        if(a.hashCode() == b.hashCode())
            return 0; //good enough
        return editDist(a, b, a.length(), b.length());
    }

    private static int editDist(String a, String b, int m, int n){
        if(m==0){
            return n;
        }
        if(n==0){
            return m;
        }

        if(a.charAt(m-1) == b.charAt(n-1)){
            return editDist(a, b, m-1, n-1);
        }
        else{
            return 1+ Math.min(editDist(a,b,m,n-1),
                    Math.min(editDist(a,b,m-1,n),
                            editDist(a,b,m-1,n-1)));
        }
    }

}
