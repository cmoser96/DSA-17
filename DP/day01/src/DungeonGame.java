public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int x = map.length;
        int y = map[0].length;

        int[][] DP = new int[y+1][x+1];
        for(int r=0; r<=x; r++){
            for(int c=0; c<=y; c++){
                DP[r][c] = 1;
            }
        }
        int cost = -healthRecursive(map, map.length-1, map[0].length-1, DP)+1;
        for(int r=0; r<x; r++){
            for(int c=0; c<y; c++){
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        return cost;
    }

    private static int healthRecursive(int[][] map, int x, int y, int[][] DP){
        if(x == 0 && y == 0){
            return Math.min(map[x][y], -1);
        }

        if(DP[x][y] != 1){
            return DP[x][y];
        }

        int cost;
        if(x == 0){
            cost = Math.min(map[x][y] + healthRecursive(map, x, y-1, DP), -1);
        }
        else if(y == 0){
            cost = Math.min(map[x][y] + healthRecursive(map, x-1, y, DP), -1);
        }
        else{
            cost = Math.min(map[x][y] +
                    Math.max(healthRecursive(map, x-1, y, DP),
                            healthRecursive(map, x, y-1, DP))
            , -1);
        }

        DP[x][y] = cost;
        return cost;
    }
}
