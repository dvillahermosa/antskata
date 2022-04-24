package code.adapt;

import java.util.Scanner;

public class App {
    private static void printMatix(int[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            System.out.println("");
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] == 0) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[X]");
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        System.out.println("LANGON's ANTS)");
        System.out.println("--------------------");
        System.out.println("Selecciona la dimension o pulsa 'E' para salir");
        int dimension = readNumber(scanner, "Dimension");
        System.out.println("Selecciona un patrón basado en 'L' y 'R', por ejemplo LRLRL");
        String pattern = scanner.next();

        try {
            Ant ant = new AntImp(pattern);
            LangtonAnts lag = new LangtonAnts(ant, dimension);

            while (!end) {
                System.out.println("Selecciona la numero de pasos o pulsa 'E' para salir");
                int pasos = readNumber(scanner, "Numero de pasos a avanzar");
                if (pasos == Integer.MIN_VALUE) {
                    end = true;
                    continue;
                }
                for (int i = 0; i < pasos; i++) {
                    lag.nextSteps(1);
                    for(int clear = 0; clear < 1000; clear++)
                    {
                        System.out.println("\b") ;
                    }
                    printMatix(lag.getGrid());
                    Thread.sleep(1000);
                }
            }

        } catch (NotCorrectPatternException ex) {
            System.out.println(ex.getMessage());
        } catch (NotCorrectMatrixDimension ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            System.out.println("--------------------");
            System.out.println("EL JUEGO HA TERMINADO");
        }
    }

    private static int readNumber(Scanner scanner, String text) {
        int result = Integer.MIN_VALUE;
        System.out.println(text);
        String c = scanner.next();
        if (c != "e" && c != "E") {
            result = Integer.parseInt(String.valueOf(c));
        }
        return result;
    }
}
