public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int x = map.length;
        int y = map[0].length;

        int[][] DP = new int[y][x];
        for(int r=0; r<x; r++){
            for(int c=0; c<y; c++){
                DP[r][c] = Integer.MAX_VALUE;
            }
        }
        int cost = -healthRecursive(map, 0, 0, DP)+1;
        for(int r=0; r<x; r++){
            for(int c=0; c<y; c++){
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        return cost;
    }

    private static int healthRecursive(int[][] map, int x, int y, int[][] DP){
        if(x == map.length-1 && y == map[0].length-1){
            return Math.min(map[x][y], -1);
        }

        if(DP[x][y] != Integer.MAX_VALUE){
            return DP[x][y];
        }

        int cost;
        if(x == map.length-1){
            cost = Math.min(map[x][y] + healthRecursive(map, x, y+1, DP), -1);
        }
        else if(y == map.length-1){
            cost = Math.min(map[x][y] + healthRecursive(map, x+1, y, DP), -1);
        }
        else{
            cost = Math.min(map[x][y] + Math.max(healthRecursive(map, x+1, y, DP), map[x][y] +
                            healthRecursive(map, x, y+1, DP)),
                    -1);
        }
        System.out.println("x: " + x + " y: " + y + " cost: " + cost);

        DP[x][y] = cost;
        return cost;
    }
}
