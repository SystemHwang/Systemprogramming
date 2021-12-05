import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server {
    static ArrayList<Integer> rank = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        rank.add(0);
        rank.add(0);
        rank.add(0);

        ServerSocket Server = new ServerSocket(2222);
        Socket socket;
        BufferedReader br;
        PrintWriter pw;

        while (true) {
            try {
                socket = Server.accept();
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String s = br.readLine();
                if (s == null) {
                    continue;
                }
                ScoreRank(Integer.parseInt(s));

                pw = new PrintWriter(socket.getOutputStream());
                pw.println(rank.get(0) + " " + rank.get(1) + " " + rank.get(2));
                pw.flush();
                System.out.println("------");
                System.out.println(rank.get(0));
                System.out.println(rank.get(1));
                System.out.println(rank.get(2));
                System.out.println("------");

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    static void ScoreRank (int n) {
        rank.add(n);
        Collections.sort(rank, Collections.reverseOrder());
        for(int i = 3; i < rank.size(); i++) {
            rank.remove(i);
        }
    }
}
