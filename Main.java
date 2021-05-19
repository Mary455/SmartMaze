import java.util.*;
public class Main {
    static int m = 20, n = 20;
    public static void main(String[] args) {
        int[][] room = new int[m][n];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                room[i][j] = 0;
            }
        }
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                System.out.print(+room[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("How many blocks do you want to make?");
        Scanner S = new Scanner(System.in);
        int t = S.nextInt();
        int counter = 0, h = 0;
        while (counter < t) {
            if (h == -1) System.out.println("ENTER YOUR COARDINATES CORRECTLY:");
            else System.out.println("ENTER YOUR COARDINATES:");
            int a = S.nextInt();
            int b = S.nextInt();
            if (a > 19 || b > 19 || a < 0 || b < 0) {
                h = -1;
                continue;
            }
            room[a][b] = 1;
            counter++;
            h = 0;
        }
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                System.out.print(+room[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        Point2D start = new Point2D(19, 0);
        Point2D location = new Point2D(19, 0);
        Point2D prelocation = new Point2D(19, 0);
        Point2D end = new Point2D(0, 19);
        ArrayList<Point2D> path = new ArrayList<>();
        path.add(new Point2D(location.x, location.y));
        ArrayList<Point2D> deniedCells = new ArrayList<>();
        while (location.x != end.x || location.y != end.y) {
            //up right:
            if (checkOpen(room, path, deniedCells, prelocation, location.x - 1, location.y + 1)) {
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x--;
                location.y++;
                path.add(new Point2D(location.x, location.y));
            }
            //right
            else if (checkOpen(room, path, deniedCells, prelocation, location.x, location.y + 1)) {
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.y++;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x - 1, location.y)) {//up
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x--;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x - 1, location.y - 1)) {//up left
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x--;
                location.y--;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x, location.y - 1)) {//left
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.y--;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x + 1, location.y)) {//down
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x++;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x + 1, location.y + 1)) {//down right
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x++;
                location.y++;
                path.add(new Point2D(location.x, location.y));
            } else if (checkOpen(room, path, deniedCells, prelocation, location.x + 1, location.y - 1)) {//down left
                prelocation.x = location.x;
                prelocation.y = location.y;
                location.x++;
                location.y--;
                path.add(new Point2D(location.x, location.y));
            } else if (!path.isEmpty()){ // go back
                deniedCells.add(new Point2D(location.x, location.y));
                path.remove(path.size() - 1);
                Point2D backcell = new Point2D(prelocation.x, prelocation.y);
                location = new Point2D(backcell.x, backcell.y);
            } else { // no way
                System.out.println("-1");
                break;
            }
        }
        for (Point2D p : path) {
            System.out.printf("x:%d , y:%d\n", p.x, p.y);
        }
    }
    public static boolean checkOpen(int[][] room, ArrayList<Point2D> path, ArrayList<Point2D> deniedCells, Point2D prelocation, int x, int y) {
        if (x == prelocation.x && y == prelocation.y) return false;
        if (x > m - 1 || x < 0 || y > n - 1 || y < 0) return false;
        if (room[x][y] == 1) return false;
        for (Point2D a : path) {
            if (a.x == x && a.y == y)
                return false;
        }
        for (Point2D a : deniedCells) {
            if (a.x == x && a.y == y)
                return false;
        }
        return true;

    }
}
class Point2D{
    int x;
    int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
