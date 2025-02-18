import java.io.*;

class Monty_Hall_and_tweet {
    static int mainFlame[][];
    static int answer = 0;
    static int selected = 0;
    static int open = 0;
    static boolean wannaChange = false;
    static int changed = 0;
    static boolean youWon = false;
        
    public static void main(String args[]) {
        String buf;
        makeRoom();
        setRoom();
        do {
            answer = (int) (Math.random() * 3);
        } while (answer == 0);
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System. in));
                System.err.print("choose one! : ");
                buf = br.readLine();
                selected = Integer.parseInt(buf);
            } catch(Exception e) {
                System.err.print("Error:" + e);
            }
        } while (selected != 1 && selected != 2 && selected != 3);
        selectOne(selected);
        setRoom();
        System.err.println("One hole gets open...");
        do {
            open = (int) (Math.random() * 3);
        } while (open == 0 || open == answer || open == selected);
        openOne(open);
        setRoom();
        System.err.println(open);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System. in));
            System.err.println("if you wanna change, put [c] : ");
            buf = br.readLine();
            if (buf.equals("c")) {
                wannaChange = true;
            }
        } catch(Exception e) {
            System.err.print("Error:" + e);
        }
        if (wannaChange) {
            unSelectOne(selected);
            for (int i = 1; i < 4; i++) {
                if (i != open && i != selected) {
                    changed = i;
                    break;
                }
            }
        } else {
            changed = selected;
        }
        selectOne(changed);
        setRoom();
        System.err.println("The answer is...");
        openAll(answer);
        setRoom();
        System.err.println(answer);
        if (changed == answer) {
            System.out.println("Awesome! You won!");
            youWon = true;
        } else {
            System.out.println("You lose...");
            youWon = false;
        }
        tweetYourResult(youWon, wannaChange);
    }

    static void makeRoom() {
        mainFlame = new int[8][19];
        for (int g = 0; g < 8; g++) {
            for (int h = 0; h < 19; h++) {
                mainFlame[g][h] = 0;
            }
        }
        for (int i = 0; i < 19; i++) {
            mainFlame[0][i] = 4;
            mainFlame[7][i] = 4;
        }
        for (int j = 1; j < 7; j++) {
            mainFlame[j][0] = 5;
            mainFlame[j][6] = 5;
            mainFlame[j][12] = 5;
            mainFlame[j][18] = 5;
        }
        // for (int k = 3; k < 6; k++) {
        //     for (int l = 2; l < 5; l++) {
        //         mainFlame[k][l] = 6;
        //         mainFlame[k][l+6] = 6;
        //         mainFlame[k][l+12] = 6;
        //     }
        // }
        mainFlame[4][3] = 7;
        mainFlame[4][9] = 7;
        mainFlame[4][15] = 7;
        mainFlame[1][3] = 1;
        mainFlame[1][9] = 2;
        mainFlame[1][15] = 3;
        /*
            ===================
            |  1  |  2  |  3  |
            |     |     |     |
            |     |     |     |
            |  ?  |  ?  |  ?  |
            |     |     |     |
            |     |     |     |
            ===================
            0 : ' '
            1 : '1'
            2 : '2'
            3 : '3'
            4 : '='
            5 : '|'
            6 : '#'
            7 : '?'
            8 : 'x'
            9 : '*'
        */
        return;
    } 

    static void setRoom() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 19; j++) {
                switch (mainFlame[i][j]) {
                    case 0:
                        System.out.print(' ');
                        break;
                    case 1:
                        System.out.print('1');
                        break;
                    case 2:
                        System.out.print('2');
                        break;
                    case 3:
                        System.out.print('3');
                        break;
                    case 4:
                        System.out.print('=');
                        break;
                    case 5:
                        System.out.print('|');
                        break;
                    case 6:
                        System.out.print('#');
                        break;
                    case 7:
                        System.out.print('?');
                        break;
                    case 8:
                        System.out.print('x');
                        break;
                    case 9:
                        System.out.print('*');
                        break;
                    default:
                        break;
                }
            }
            System.out.print("\n");
        }
        return;
    }

    static void selectOne(int a) {
        for (int i = 3; i < 6; i++) {
            for (int k = 2; k < 5; k++) {
                if (a == 1) {
                    mainFlame[i][k] = 6;
                } else if (a == 2) {
                    mainFlame[i][k+6] = 6;
                } else if (a == 3) {
                    mainFlame[i][k+12] = 6;
                }
            }
        }
        if (a == 1) {
            mainFlame[4][3] = 7;
        } else if (a == 2) {
            mainFlame[4][9] = 7;
        } else if (a == 3) {
            mainFlame[4][15] = 7;
        }
        return;
    }

    static void unSelectOne(int a) {
        for (int i = 3; i < 6; i++) {
            for (int k = 2; k < 5; k++) {
                if (a == 1) {
                    mainFlame[i][k] = 0;
                } else if (a == 2) {
                    mainFlame[i][k+6] = 0;
                } else if (a == 3) {
                    mainFlame[i][k+12] = 0;
                }
            }
        }
        if (a == 1) {
            mainFlame[4][3] = 7;
        } else if (a == 2) {
            mainFlame[4][9] = 7;
        } else if (a == 2) {
            mainFlame[4][15] = 7;
        }
        return;
    }

    static void openOne(int a) {
        if (a == 1) {
            mainFlame[4][3] = 8;
        } else if (a == 2) {
            mainFlame[4][9] = 8;
        } else if (a == 3) {
            mainFlame[4][15] = 8;
        }
    }

    static void openAll(int a) {
        switch (a) {
            case 1:
                mainFlame[4][3] = 9;
                mainFlame[4][9] = 8;
                mainFlame[4][15] = 8;
                break;
            case 2:
                mainFlame[4][3] = 8;
                mainFlame[4][9] = 9;
                mainFlame[4][15] = 8;
                break;
            case 3:
                mainFlame[4][3] = 8;
                mainFlame[4][9] = 8;
                mainFlame[4][15] = 9;
                break;
            default:
                break;
        }
    }

    static void tweetYourResult(boolean youWon, boolean wannaChange) {
        String buf;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System. in));
            System.err.println("To tweet your result, put [t].");
            buf = br.readLine();
            if (buf.equals("t")) {
                if (youWon) {
                    if (wannaChange) {
                        try {
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E3%83%86%E3%82%B9%E3%83%88%E3%80%91+%0A+I+won+%22FallHole%22%21%21%0A%28%20%5E%20%5E%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E7%94%A8%E3%80%91+%0A%0A+I+changed+the+door+and+won+%21%21%21%0A%28%20%5E%20%5E%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=【プログラミング課題用】+%0A%0A+I+changed+the+door+and+won+%21%21%21%0A%28%20%5E%20%5E%20%29");
                            ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90SZPP%E3%80%91%0D%0A%0D%0AI%20changed%20the%20door%20and%20won%20%21%21");
                            Process process = builder.start();
                            process.waitFor();
                        } catch (Exception e) {
                            System.err.println("ERROR!!" + e);
                        }
                    } else {
                        try {
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E3%83%86%E3%82%B9%E3%83%88%E3%80%91+%0A+I+won+%22FallHole%22%21%21%0A%28%20%5E%20%5E%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E7%94%A8%E3%80%91+%0A%0A+I+didn't+change+the+door+and+won+%21%21%21%0A%28%20%5E%20%5E%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=【プログラミング課題用】+%0A%0A+I+didn't+change+the+door+and+won+%21%21%21%0A%28%20%5E%20%5E%20%29");
                            ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90SZPP%E3%80%91%0D%0A%0D%0AI%20did%20not%20change%20the%20door%20and%20won%20%21%21");
                            Process process = builder.start();
                            process.waitFor();
                        } catch (Exception e) {
                            System.err.println("ERROR!!" + e);
                        }
                    }
                } else {
                    if (wannaChange) {
                        try {
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E3%83%86%E3%82%B9%E3%83%88%E3%80%91+%0A+I+lost+%22FallHole%22%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E7%94%A8%E3%80%91+%0A%0A+I+changed+the+door+and+lost+%E2%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=【プログラミング課題用】+%0A%0A+I+changed+the+door+and+lost+%E2%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90SZPP%E3%80%91%0D%0A%0D%0AI%20changed%20the%20door%20and%20lost%2E%2E%2E");
                            Process process = builder.start();
                            process.waitFor();
                        } catch (Exception e) {
                            System.err.println("ERROR!!" + e);
                        }
                    } else {
                        try {
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E3%83%86%E3%82%B9%E3%83%88%E3%80%91+%0A+I+lost+%22FallHole%22%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%AA%B2%E9%A1%8C%E7%94%A8%E3%80%91+%0A%0A+I+didn't+change+the+door+and+lost+%E2%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            // ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=【プログラミング課題用】+%0A%0A+I+didn't+change+the+door+and+lost+%E2%E2%80%A6%0A%28%20%3B%20%3B%20%29");
                            ProcessBuilder builder = new ProcessBuilder("firefox", "-url", "https://x.com/intent/post?text=%E3%80%90SZPP%E3%80%91%0D%0A%0D%0AI%20did%20not%20change%20the%20door%20and%20lost%2E%2E%2E");
                            Process process = builder.start();
                            process.waitFor();
                        } catch (Exception e) {
                            System.err.println("ERROR!!" + e);
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.err.print("Error:" + e);
        }
        return;
    }
}