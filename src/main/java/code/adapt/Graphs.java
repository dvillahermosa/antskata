package code.adapt;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
//This is only a proof of concept with swing
class GridsCanvas extends JPanel {
    int width, height;

    int rows;

    int cols;
    int paso = 1;
    private LangtonAnts lag = null;

    GridsCanvas(int w, int h, int r, int c) {
        setSize(width = w, height = h);
        rows = r;
        cols = c;
    }

    void setLag(LangtonAnts lag) {
        this.lag = lag;
    }

    public void paint(Graphics g) {
        int i;
        width = getSize().width;
        height = getSize().height - 100;

        // draw the rows
        int rowHt = height / (rows);
        int rowWid = width / (cols);
        g.drawString("                          " + paso, 0, 100);
        ;
        g.drawString("Paso = " + paso, 0, 100);

        for (int x = 0; x < lag.getGrid().length; x++) {
            for (int y = 0; y < lag.getGrid().length; y++) {
                if (lag.getGrid()[x][y] == 0) {
                    g.setColor(Color.white);

                } else {
                    g.setColor(Color.BLUE);
                }
                g.fill3DRect(rowWid * x, rowHt * y + 100, rowWid, rowWid, true);
            }
        }
        paso++;
    }
}

public class Graphs extends JFrame {
    LangtonAnts lag;
    GridsCanvas xyz;

    public LangtonAnts getLag() {
        return this.lag;
    }

    public Graphs() throws NotCorrectPatternException, NotCorrectMatrixDimension {
        xyz = new GridsCanvas(300, 200, 11, 11);
        add(xyz);
        Ant ant = new AntImp("RL");
        lag = new LangtonAnts(ant, 11);
        xyz.setLag(lag);
        xyz.setPreferredSize(new Dimension(1000, 1000));

        pack();
    }

    public static void main(String[] a) throws NotCorrectPatternException, NotCorrectMatrixDimension, InterruptedException {
        Graphs grap = new Graphs();
        grap.setVisible(true);
        for (int i = 0; i < 1000000; i++) {
            Thread.sleep(100);
            grap.getLag().nextSteps(1);
            grap.refresh();
        }
    }

    private void refresh() {
        this.xyz.updateUI();
    }
}