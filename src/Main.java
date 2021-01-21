public class Main {

    private static final int MAX_CLIENTS = 11;
    private static final int MAX_REPLENISHERS = 6;
    private static final String[] names = {"Ivan", "Àlvar", "Narcís", "Virgínia", "Jessica", "Jonàs", "Pere", "Teix", "Aloma", "Remei"};

    public static void main(String[] args) {

        //Crear nombre de clients i reposadors aleatori.
        int clientsNumber = (int) (Math.random() * MAX_CLIENTS);
        int replenishersNumber = (int) (Math.random() * MAX_REPLENISHERS);

        //Monitor
        DrinkMachineMonitor monitor = new DrinkMachineMonitor(clientsNumber, replenishersNumber);

        Thread[] molecules = new Thread[clientsNumber + replenishersNumber];

        //Missatge d'inici.
        System.out.println("COMENÇA LA SIMULACIÓ");
        System.out.println("Avui hi ha " + clientsNumber + " clients i " + replenishersNumber + " reposadors\n" +
                "La màquina de refrescs està buida, hi caben " + DrinkMachineMonitor.MACHINE_CAPACITY + " refrescs");

        //Creació dels clients
        for (int i = 0; i < clientsNumber; i++) {
            molecules[i] = new Thread(new Client(names[i], monitor));
            molecules[i].start();
        }

        //Interval d'espera entre clients i reposadors.
        sleep(200);

        //Creació dels reposadors.
        for (int i = clientsNumber; i < clientsNumber + replenishersNumber; i++) {
            molecules[i] = new Thread(new Replenisher(i - clientsNumber + 1, monitor));
            molecules[i].start();
        }

        //Espera a que tots arribin a aquest punt.
        for (Thread molecule : molecules) {
            join(molecule);
        }

        System.out.println("ACABA LA SIMULACÍO");
    }

    /**
     * Mètode per cridar al join d'un fil en concret.
     *
     * @param thread Fil del que es vol fer el join.
     */
    private static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode per fer esperes.
     *
     * @param ms Milisegons que ha de durar l'espera.
     */
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}