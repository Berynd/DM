package DM;
import java.util.*;

public class MasterMind {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Random random = new Random();
            int[] codeSecret = new int[5];
            boolean[] utilise = new boolean[10];

            for (int i = 0; i < 5; i++) {
                int chiffre;
                do {
                    chiffre = random.nextInt(9) + 1;
                }
                while (utilise[chiffre]);
                codeSecret[i] = chiffre;
                utilise[chiffre] = true;
            }

            System.out.println("Bienvenue au jeu MasterMind !");
            System.out.println("Trouvez le code secret (5 chiffres entre 1 et 9, sans répétition). Vous avez 12 essais.");

            int essaisRestants = 12;
            boolean codeTrouve = false;

            while (essaisRestants > 0 && !codeTrouve) {
                System.out.println("Essais restants : " + essaisRestants);
                System.out.print("Entrez votre proposition (5 chiffres entre 1 et 9, sans répétition) : ");
                String proposition = scanner.nextLine();

                if (proposition.length() != 5) {
                    System.out.println("Entrée invalide. Vous devez entrer 5 chiffres.");
                }
                else {
                    int[] propositionInt = new int[5];
                    boolean propositionValide = true;

                    for (int i = 0; i < 5; i++) {
                        if (proposition.charAt(i) < '1' || proposition.charAt(i) > '9') {
                            System.out.println("Entrée invalide. Chaque chiffre doit être entre 1 et 9.");
                            propositionValide = false;
                            break;
                        }
                        propositionInt[i] = proposition.charAt(i) - '0';
                    }

                    if (propositionValide) {
                        boolean doublon = false;
                        for (int i = 0; i < 5; i++) {
                            for (int j = i + 1; j < 5; j++) {
                                if (propositionInt[i] == propositionInt[j]) {
                                    doublon = true;
                                    break;
                                }
                            }
                        }

                        if (doublon) {
                            System.out.println("Entrée invalide. Les chiffres doivent être uniques.");
                        }
                        else {
                            int bienPlaces = 0;
                            int malPlaces = 0;

                            for (int i = 0; i < 5; i++) {
                                if (propositionInt[i] == codeSecret[i]) {
                                    bienPlaces++;
                                }
                                else {
                                    for (int j = 0; j < 5; j++) {
                                        if (propositionInt[i] == codeSecret[j]) {
                                            malPlaces++;
                                            break;
                                        }
                                    }
                                }
                            }

                            if (bienPlaces == 5) {
                                System.out.println("Félicitations ! Vous avez trouvé le code secret !");
                                codeTrouve = true;
                            }
                            else {
                                System.out.println("Bien placés : " + bienPlaces + ", Mal placés : " + malPlaces);
                            }
                        }
                    }
                }

                essaisRestants--;
            }

            if (!codeTrouve) {
                System.out.print("Dommage ! Le code secret était : ");
                for (int i = 0; i < 5; i++) {
                    System.out.print(codeSecret[i]);
                }
                System.out.println();
            }

            System.out.print("Voulez-vous rejouer ? (o/n) : ");
            String reponse = scanner.nextLine();

            if (!reponse.equalsIgnoreCase("o")) {
                break;
            }
        }

        System.out.println("Merci d'avoir joué !");
    }
}
