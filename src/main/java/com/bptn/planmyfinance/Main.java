package com.bptn.planmyfinance;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public static void showAppLogo() {
        System.out.println("\n" +
                "  ____  _               __  __         _____ _                            \n" +
                " |  _ \\| | __ _ _ __   |  \\/  |_   _  |  ___(_)_ __   __ _ _ __   ___ ___ \n" +
                " | |_) | |/ _` | '_ \\  | |\\/| | | | | | |_  | | '_ \\ / _` | '_ \\ / __/ _ \\\n" +
                " |  __/| | (_| | | | | | |  | | |_| | |  _| | | | | | (_| | | | | (_|  __/\n" +
                " |_|   |_|\\__,_|_| |_| |_|  |_|\\__, | |_|   |_|_| |_|\\__,_|_| |_|\\___\\___|\n" +
                "                               |___/                                      \n");
    }
}