import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TetrisEx extends JFrame {
    TetrisPanel TP = new TetrisPanel();
    JDialog JD = new JDialog();
    TetrisThread th;

    ServerTCP serverTCP = new ServerTCP();

    static int blocksize = 20;

    int End = 0;
    int random = 0 , random2 = (int)(Math.random()*7);

    int score = 0;

    int wid = 100;
    int hgt = 0;
    int rotation = 0;

    boolean limit = false;

    int curX[]= new int[4], curY[] = new int [4];

    int blocks[][][][]  =
            {
                    {

                            {
                                    {0,0,0,0},
                                    {1,0,0,0},
                                    {1,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,1,0},
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {1,1,1,0},
                                    {0,0,1,0},
                                    {0,0,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,1,0},
                                    {0,0,1,0},
                                    {0,1,1,0},
                                    {0,0,0,0}
                            }
                    },
                    {

                            {
                                    {0,0,0,0},
                                    {0,0,1,0},
                                    {1,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,1,0},
                                    {1,0,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,1,0},
                                    {0,0,1,0},
                                    {0,0,1,0},
                                    {0,0,0,0}
                            }
                    },
                    {
                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            }
                    },
                    {
                            {
                                    {0,0,0,0},
                                    {0,0,0,0},
                                    {1,1,1,1},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,1,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {0,0,0,0},
                                    {1,1,1,1},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,1,0,0},
                                    {0,1,0,0}
                            }
                    },
                    {

                            {
                                    {0,0,0,0},
                                    {0,1,0,0},
                                    {1,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,1,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,1,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {1,1,0,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            }
                    },
                    {

                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {0,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,1,0},
                                    {0,1,1,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {1,1,0,0},
                                    {0,1,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,1,0},
                                    {0,1,1,0},
                                    {0,1,0,0},
                                    {0,0,0,0}
                            }
                    },
                    {

                            {
                                    {0,0,0,0},
                                    {0,1,1,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,1,0},
                                    {0,0,1,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,0,0,0},
                                    {0,1,1,0},
                                    {1,1,0,0},
                                    {0,0,0,0}
                            },
                            {
                                    {0,1,0,0},
                                    {0,1,1,0},
                                    {0,0,1,0},
                                    {0,0,0,0}
                            }
                    }
            };

    int[][] gameboard = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    JButton btn = new JButton("재도전");
    JButton btn2 = new JButton("나가기");


    JLabel lbl = new JLabel();
    JLabel lbl2 = new JLabel();
    JLabel lbRank = new JLabel();
    JLabel lbRank1 = new JLabel();
    JLabel lbRank2 = new JLabel();
    JLabel lbRank3 = new JLabel();


    TetrisEx(){
        setTitle("테트리스");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        TP.setSize(720,600);

        add(TP);

        th = new TetrisThread();

        // JDialog
        JD.setTitle("점수");
        JD.setSize(250,220);
        JD.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));


        lbl.setFont(new Font("arial",Font.PLAIN,15));
        lbl2.setText("점  수");
        lbl2.setFont(new Font("나눔고딕",Font.PLAIN,15));
        lbRank.setText("랭  킹");
        lbRank.setFont(new Font("나눔고딕",Font.PLAIN,15));
        lbRank1.setText("");
        lbRank1.setFont(new Font("나눔고딕",Font.PLAIN,15));
        lbRank2.setText("");
        lbRank2.setFont(new Font("나눔고딕",Font.PLAIN,15));
        lbRank3.setText("");
        lbRank3.setFont(new Font("나눔고딕",Font.PLAIN,15));

        serverTCP.run();
        while (true) {
            if (serverTCP.isDone) {
                String [] rank = serverTCP.getRank();
                lbRank1.setText(rank[0]);
                lbRank2.setText(rank[1]);
                lbRank3.setText(rank[2]);
                break;
            }
        }

        TP.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_UP)
                    TP.moveUp();
                if(keyCode == KeyEvent.VK_DOWN)
                    TP.moveDown();
                if(keyCode == KeyEvent.VK_LEFT)
                    TP.moveLeft();
                if(keyCode == KeyEvent.VK_RIGHT)
                    TP.moveRight();
            }
        });

        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                limit = false;

                serverTCP.score = score;
                serverTCP.run();

                while (true) {
                    if (serverTCP.isDone) {
                        String [] rank = serverTCP.getRank();
                        lbRank1.setText(rank[0]);
                        lbRank2.setText(rank[1]);
                        lbRank3.setText(rank[2]);
                        break;
                    }
                }
                for(int y=0; y<19;y++)
                    for(int x=1; x<12; x++)
                        gameboard[y][x] =0 ;
                score =0;
                wid =100; hgt = 0;
                JD.setVisible(false);
            }
        });

        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new MainFrame();

                serverTCP.score = score;
                serverTCP.run();

                while (true) {
                    if (serverTCP.isDone) {
                        String [] rank = serverTCP.getRank();
                        lbRank1.setText(rank[0]);
                        lbRank2.setText(rank[1]);
                        lbRank3.setText(rank[2]);
                        break;
                    }
                }
                JD.setVisible(false);
                dispose();
            }
        });

        TP.setBackground(Color.WHITE);
        setSize(530,520);
        setVisible(true);

        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        JD.setLocation((screenSize.width - frameSize.width)/2 + 220, (screenSize.height - frameSize.height)/2 +220);

        TP.requestFocus(true);
        th.start();
    }

    class TetrisPanel extends JPanel{
        public void paintComponent(Graphics g){

            int cnt = 0 , cnt2 = 0;
            TP.requestFocus(true);
            super.paintComponent(g);

            lbl2.setLocation(360,120);
            TP.add(lbl2);

            lbl.setLocation(360,145);
            TP.add(lbl);

            lbRank.setLocation(360,200);
            TP.add(lbRank);

            lbRank1.setLocation(360,225);
            TP.add(lbRank1);
            lbRank2.setLocation(360,245);
            TP.add(lbRank2);
            lbRank3.setLocation(360,265);
            TP.add(lbRank3);

            lbl.setText(Integer.toString(score*100));

            blockLookAhead(g);

            gameOverCheck();

            removeBlock(cnt, cnt2, g);

            blockToWall();

            makeBlock(g);

            makeBorder(g);

            if(End == 1){
                random2 = (int)(Math.random()*7);
                End = 0;
            }
        }

        public void blockLookAhead(Graphics g){
            for(int a = 0; a<4 ;a++){
                for(int b = 0; b<4;b++){
                    if(blocks[random2][0][a][b] == 1){
                        g.fill3DRect(b*blocksize+120, a*blocksize, blocksize, blocksize, true);

                    }
                    switch (random2) {
                        case 0:
                            g.setColor(Color.yellow);
                            break;
                        case 1:
                            g.setColor(Color.pink);
                            break;
                        case 2:
                            g.setColor(Color.blue);
                            break;
                        case 3:
                            g.setColor(Color.red);
                            break;
                        case 4:
                            g.setColor(Color.green);
                            break;
                        case 5:
                            g.setColor(Color.orange);
                            break;
                        case 6:
                            g.setColor(Color.CYAN);
                            break;
                    }
                }
            }
        }

        public void gameOverCheck(){
            for(int x=1;x<12;x++)
                if(gameboard[2][x]==1){
                    limit = true;

                    JD.add(lbl);
                    JD.add(btn);
                    JD.add(btn2);

                    JD.setVisible(true);
                }
        }

        public void removeBlock(int cnt, int cnt2, Graphics g){
            for(int y =0;y<19;y++){
                for(int x =1; x<12 ; x++){
                    if(gameboard[y][x] == 1){
                        cnt2++;
                    }
                }
                if(cnt2 == 11){
                    for(int i=y;i>1;i--)
                        for(int j=1;j<13;j++){
                            gameboard[i][j] = 0;
                            gameboard[i][j] = gameboard[i-1][j];
                        }
                    score++;
                }else{
                    blockDown(cnt,g);
                }
                cnt2 = 0 ;
            }
        }

        public void makeBlock(Graphics g){
            g.setColor(Color.GRAY);
            for(int y=0; y<19;y++){
                for(int x=1; x<12; x++){
                    if(gameboard[y][x]== 1){
                        g.fill3DRect( x*blocksize+20, y*blocksize+60, blocksize, blocksize, true);

                    }
                }
            }
        }

        public void blockDown(int cnt, Graphics g){
            for(int j = 0; j<4 ;j++){
                for(int k = 0; k<4;k++){
                    if(blocks[random][rotation][j][k] == 1){
                        curX[cnt] = ((k*blocksize)+wid)/blocksize; curY[cnt] = ((j*blocksize)+hgt)/blocksize;
                        g.fill3DRect(curX[cnt]*blocksize+20, curY[cnt]*blocksize+60, blocksize, blocksize, true);
                        switch (random) {
                            case 0:
                                g.setColor(Color.yellow);
                                break;
                            case 1:
                                g.setColor(Color.pink);
                                break;
                            case 2:
                                g.setColor(Color.blue);
                                break;
                            case 3:
                                g.setColor(Color.red);
                                break;
                            case 4:
                                g.setColor(Color.green);
                                break;
                            case 5:
                                g.setColor(Color.orange);
                                break;
                            case 6:
                                g.setColor(Color.CYAN);
                                break;
                        }
                        cnt++;
                    }
                }
            }
        }

        public void blockToWall(){
            try{
                for(int z = 0; z<4 ; z++)
                    if(gameboard[curY[z]+1][curX[z]] == 1)
                        for (int j= 0; j<4;j++){

                            gameboard[curY[j]][curX[j]] = 1;
                            wid =100;
                            hgt =0;
                            End = 1;
                            rotation = 0;
                            random = random2;
                        }
            }catch(ArrayIndexOutOfBoundsException e){ System.out.println("Error");
                for(int i=0; i<4 ; i++)
                    System.out.print("("+curY[i]+","+curX[i]+")");
                System.out.println();}


        }

        public int collision_LEFT(){
            for(int i=0; i<4; i++){
                if(gameboard[curY[i]][curX[i]-1] == 1)
                    return 1;
            }
            return 0;
        }

        public int collision_RIGHT(){

            for(int i=0; i<4; i++){
                if(gameboard[curY[i]][curX[i]+1] == 1)
                    return 1;
            }
            return 0;
        }

        public void rotationCheck(){
            int cnt2=0;
            for(int j = 0; j<4 ;j++){
                for(int k = 0; k<4;k++){
                    int rotation2 = (rotation%4)+1 ;
                    if(rotation2 == 4)
                        rotation2 = 0;
                    if(blocks[random][rotation2][j][k] == 1){
                        curX[cnt2] = ((k*blocksize)+wid)/blocksize; curY[cnt2] = ((j*blocksize)+hgt)/blocksize;
                        cnt2++;
                    }
                }
            }

            int chk = 0;
            int blank =0;
            int error = 0;

            if(gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1) || (random == 1 && gameboard[curY[1]][curX[1]] ==1 )){
                chk = 1;
                error++;
                System.out.println("chk1");
                if(random == 3){
                    for(int i=1;i<5;i++)
                        if(gameboard[curY[0]][curX[0]+i] == 0)
                            blank++;
                    if(blank < 4)
                        chk = 4;

                    System.out.println(blank);
                }else{
                    for(int i=1; i<4;i++)
                        if(gameboard[curY[0]][curX[0]+i] == 0)
                            blank++;
                    if(blank <3)
                        chk = 4;
                    System.out.println("blank2");
                    System.out.println(blank);
                }

            } else if(gameboard[curY[2]][curX[2]] == 1){
                error++;
                chk = 2;
                System.out.println("chk2");

                for(int i=1; i<5;i++)
                    if(gameboard[curY[2]][curX[2]-i] == 0)
                        blank ++;
                if(blank < 4)
                    chk = 4;
                System.out.println("blank2");
                System.out.println(blank);


            } else if(gameboard[curY[3]][curX[3]] == 1){
                error++;
                chk = 3;
                System.out.println("chk3");
                for(int i=0; i<5;i++)
                    if(gameboard[curY[3]][curX[3]-i] == 0)
                        blank ++;
                if(blank < 4)
                    chk = 4;
                System.out.println(blank);

            }

            if(chk == 1){
                wid += blocksize;
                rotation++;
                rotation = rotation%4;
            }else if (chk ==2){
                wid -= blocksize*2;
                rotation++;
                rotation = rotation%4;
            }else if (chk ==3){
                wid -= blocksize;
                rotation++;
                rotation = rotation%4;
            }else if(chk == 4){
                System.out.println("ban");
            }else{
                rotation++;
                rotation = rotation%4;
            }


        }

        public void makeBorder(Graphics g){
            g.setColor(Color.GRAY);

            g.draw3DRect(39, 66, 1, 373,true);
            g.draw3DRect(259, 66, 1, 373, true);
            g.draw3DRect(39, 439, 221, 1,true);
            g.draw3DRect(39, 65, 221, 1, true);
        }

        void down(){
            hgt +=blocksize;
            TP.repaint();
        }
        void moveUp(){
            rotationCheck();
            if(limit == false)
                repaint();
        }
        void moveDown(){
            if(limit == false){
                hgt += blocksize;
                TP.repaint();
            }
        }
        void moveLeft(){
            int sel = collision_LEFT();
            if(sel == 0 && limit == false){
                wid -= blocksize;
                TP.repaint();
            }
        }
        void moveRight(){
            int sel = collision_RIGHT();
            if(sel == 0 && limit == false){
                wid += blocksize;
                TP.repaint();
            }
        }
    }

    class TetrisThread extends Thread{
        TetrisPanel TP = new TetrisPanel();
        public void run(){
            while(true){
                try{
                    sleep(500);
                    if(!limit)
                        TP.down();
                }catch(InterruptedException e){
                    return;
                }
            }
        }
    }
    public static void main(String[] args) {
        new MainFrame();
    }
}

class ServerTCP extends Thread {
    final static String IP = "14.56.86.113";
    final static int PORT = 2222;

    Socket socket;
    PrintWriter pw;
    BufferedReader br;
    String [] rank;
    int score = 0;
    boolean isDone = false;

    public ServerTCP(){

    }

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket(IP, PORT);
            pw = new PrintWriter(socket.getOutputStream());
            pw.println(score * 100);
            pw.flush();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            rank = br.readLine().split(" ");

        } catch (Exception a) {
            System.out.println(a.getMessage());
        } finally {
            try {
                if (socket != null)
                    socket.close();
                if (br != null)
                    br.close();
                if (pw != null)
                    pw.close();
            } catch (IOException b) {
                b.printStackTrace();
            }
        }
        isDone = true;
    }

    public String [] getRank(){
        isDone = false;
        return rank;
    }
}

class MainFrame extends JFrame {

    public MainFrame() {


        setTitle("테트리스 온라인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setSize(290,300);
        setVisible(true);

        setBackground(Color.WHITE);

        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);

        requestFocus(true);

        JButton btn = new JButton("시작");

        JLabel txt = new JLabel();
        txt.setFont(new Font("나눔고딕",Font.PLAIN,15));
        txt.setText("최  고  랭  킹");

        JLabel lbRank = new JLabel();
        lbRank.setFont(new Font("나눔고딕",Font.PLAIN,15));

        btn.setPreferredSize(new Dimension(90, 50));
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 30));
        c.add(txt);
        c.add(lbRank);
        c.add(btn);

        ServerTCP serverTCP = new ServerTCP();
        serverTCP.run();

        while (true) {

            if (serverTCP.isDone) {
                String[] rank = serverTCP.getRank();
                lbRank.setText(rank[0]);
                break;
            }

        }


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TetrisEx();
                setVisible(false);
            }
        });
    }
}
