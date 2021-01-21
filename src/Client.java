public class Client implements Runnable {

    private static final int MAX_DRINKS = 5; //Quantitat màxima de begudes que podrà agafar un client.
    private final String name; //Nom del client.
    private int drinkNumber; //Quantitat de begudes que ha agafat.

    // Declaració del monitor.
    private final DrinkMachineMonitor monitor;

    public Client(String name, DrinkMachineMonitor monitor) {
        this.name = name;
        this.monitor = monitor;
    }

    @Override
    public void run() {

        //Càlcul del nombre de begudes que prendrà el client.
        int totalDrinks = (int) (Math.random() * MAX_DRINKS);
        System.out.println("\t\t" + name + " arriba i farà " + totalDrinks + " consumicions");

        //Comprovar que el client vulgui prendre alguna beguda.
        if (totalDrinks > 0) {

            //Comprovar que hi ha resposadors
            if (monitor.hasReplenishers()) {

                //Bucle fins a la quantitat total de begudes que ha de prendre el client concret.
                for (int i = 0; i < totalDrinks; i++) {
                    monitor.takeProduct(); //Agafar producte.
                    System.out.println(name + " agafa un refresc - consumició: " + ++drinkNumber);
                }
            } else { //No hi ha reposadors.
                System.out.println(name + ": Aquí no hi ha nigú per a la màquina!!!!");
            }
        }

        // El client se'n va.
        monitor.clientLeaves();
        System.out.println("------> " + name + " se'n va, queden " + monitor.getClientsQuantity() + " clients");
    }
}
